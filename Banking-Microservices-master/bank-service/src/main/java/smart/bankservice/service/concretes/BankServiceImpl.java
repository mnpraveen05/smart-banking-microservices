package smart.bankservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import smart.bankservice.entity.Bank;
import smart.bankservice.entity.dto.BankDTO;
import smart.bankservice.entity.request.BankSaveRequest;
import smart.bankservice.entity.request.BankUpdateRequest;
import smart.bankservice.repository.BankRepository;
import smart.bankservice.service.abstracts.BankService;
import smart.bankservice.service.abstracts.mapper.BankMapper;
import smart.bankservice.utils.constant.Caches;
import smart.bankservice.utils.rabbitMQ.enums.LogType;
import smart.bankservice.utils.rabbitMQ.enums.OperationType;
import smart.bankservice.utils.rabbitMQ.producer.LogProducer;
import smart.bankservice.utils.rabbitMQ.request.LogRequest;
import smart.bankservice.utils.constant.ExceptionMessages;
import smart.bankservice.utils.constant.Messages;
import smart.bankservice.utils.exception.customExceptions.BankNotFoundException;
import smart.bankservice.utils.result.DataResult;
import smart.bankservice.utils.result.Result;
import smart.bankservice.utils.result.SuccessDataResult;
import smart.bankservice.utils.result.SuccessResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final LogProducer logProducer;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository, LogProducer logProducer) {
        this.bankRepository = bankRepository;
        this.logProducer = logProducer;
    }

    @CacheEvict(value = Caches.BANKS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<BankDTO> save(BankSaveRequest bankSaveRequest) {
        Bank bank = BankMapper.INSTANCE.convertToSaveBank(bankSaveRequest);
        this.bankRepository.save(bank);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.BANK_CREATED));

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertToBankDTO(bank),
                Messages.BANK_CREATED
        );
    }

    @CachePut(value = Caches.BANK_CACHE, key = "#bankUpdateRequest.id()", unless = "#result.success != true")
    @CacheEvict(value = Caches.BANKS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<BankDTO> update(BankUpdateRequest bankUpdateRequest) {
        if(!this.bankRepository.existsById(bankUpdateRequest.id()))
            throw new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND);

        Bank bank = BankMapper.INSTANCE.convertToUpdateBank(bankUpdateRequest);
        this.bankRepository.save(bank);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.BANK_UPDATED));

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertToBankDTO(bank),
                Messages.BANK_UPDATED
        );
    }

    @Caching(
            evict = {
                    @CacheEvict(value = Caches.BANKS_CACHE, allEntries = true, condition = "#result.success != false"),
                    @CacheEvict(value = Caches.BANK_CACHE, key = "#bankId", condition = "#result.success != false")
            }
    )
    @Override
    public Result deleteById(Long bankId) {
        Bank bank = this.bankRepository
                .findById(bankId)
                .orElseThrow(() -> new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND));

        this.bankRepository.deleteById(bank.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.BANK_DELETED));

        return new SuccessResult(Messages.BANK_DELETED);
    }

    @Cacheable(value = Caches.BANK_CACHE, key = "#bankId", unless = "#result.success != true")
    @Override
    public DataResult<BankDTO> findById(Long bankId) {
        BankDTO bankDTO = this.bankRepository
                .findById(bankId)
                .map(BankMapper.INSTANCE::convertToBankDTO)
                .orElseThrow(() -> new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.BANK_FOUND));

        return new SuccessDataResult<>(bankDTO, Messages.BANK_FOUND);
    }

    @Cacheable(value = Caches.BANKS_CACHE, key = "'all'", unless = "#result.success != true")
    @Override
    public DataResult<List<BankDTO>> findAll() {
        List<Bank> bankList = this.bankRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.BANKS_LISTED));

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertBankDTOs(bankList),
                Messages.BANKS_LISTED
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName("bank-service")
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
