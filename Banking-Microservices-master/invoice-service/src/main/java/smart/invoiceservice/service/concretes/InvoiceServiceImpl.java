package smart.invoiceservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smart.invoiceservice.entity.Invoice;
import smart.invoiceservice.entity.dto.InvoiceDTO;
import smart.invoiceservice.entity.request.InvoiceSaveRequest;
import smart.invoiceservice.entity.request.InvoiceUpdateRequest;
import smart.invoiceservice.repository.InvoiceRepository;
import smart.invoiceservice.service.abstracts.InvoiceService;
import smart.invoiceservice.service.abstracts.mapper.InvoiceMapper;
import smart.invoiceservice.utils.constant.Caches;
import smart.invoiceservice.utils.rabbitMQ.enums.LogType;
import smart.invoiceservice.utils.rabbitMQ.enums.OperationType;
import smart.invoiceservice.utils.rabbitMQ.producer.LogProducer;
import smart.invoiceservice.utils.rabbitMQ.request.LogRequest;
import smart.invoiceservice.utils.client.UserServiceClient;
import smart.invoiceservice.utils.client.dto.RestResponse;
import smart.invoiceservice.utils.client.dto.UserResponse;
import smart.invoiceservice.utils.constant.ExceptionMessages;
import smart.invoiceservice.utils.constant.Messages;
import smart.invoiceservice.utils.exception.customExceptions.InvoiceNotFoundException;
import smart.invoiceservice.utils.result.DataResult;
import smart.invoiceservice.utils.result.Result;
import smart.invoiceservice.utils.result.SuccessDataResult;
import smart.invoiceservice.utils.result.SuccessResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserServiceClient userServiceClient;
    private final LogProducer logProducer;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserServiceClient userServiceClient, LogProducer logProducer) {
        this.invoiceRepository = invoiceRepository;
        this.userServiceClient = userServiceClient;
        this.logProducer = logProducer;
    }

    @CacheEvict(value = Caches.INVOICES_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<InvoiceDTO> save(InvoiceSaveRequest invoiceSaveRequest) {
        userServiceClient.findById(invoiceSaveRequest.userId());

        Invoice invoice = InvoiceMapper.INSTANCE.convertToSaveInvoice(invoiceSaveRequest);
        this.invoiceRepository.save(invoice);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.INVOICE_CREATED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice),
                Messages.INVOICE_CREATED
        );
    }

    @CachePut(value = Caches.INVOICE_CACHE, key = "#invoiceUpdateRequest.id()", unless = "#result.success != true")
    @CacheEvict(value = Caches.INVOICES_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<InvoiceDTO> update(InvoiceUpdateRequest invoiceUpdateRequest) {
        userServiceClient.findById(invoiceUpdateRequest.userId());

        if(!this.invoiceRepository.existsById(invoiceUpdateRequest.id()))
            throw new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND);

        Invoice invoice = InvoiceMapper.INSTANCE.convertToUpdateInvoice(invoiceUpdateRequest);
        this.invoiceRepository.save(invoice);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.INVOICE_UPDATED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice),
                Messages.INVOICE_UPDATED
        );
    }

    @Caching(
            evict = {
                    @CacheEvict(value = Caches.INVOICES_CACHE, allEntries = true, condition = "#result.success != false"),
                    @CacheEvict(value = Caches.INVOICE_CACHE, key = "#invoiceId", condition = "#result.success != false")
            }
    )
    @Override
    public Result deleteById(Long invoiceId) {
        Invoice invoice = this.invoiceRepository
                .findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND));

        this.invoiceRepository.deleteById(invoice.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.INVOICE_DELETED));

        return new SuccessResult(Messages.INVOICE_DELETED);
    }

    @Cacheable(value = Caches.INVOICE_CACHE, key = "#invoiceId", unless = "#result.success != true")
    @Override
    public DataResult<InvoiceDTO> findById(Long invoiceId) {
        InvoiceDTO invoiceDTO = this.invoiceRepository
                .findById(invoiceId)
                .map(InvoiceMapper.INSTANCE::convertToInvoiceDTO)
                .orElseThrow(() -> new InvoiceNotFoundException(ExceptionMessages.INVOICE_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICE_FOUND));

        return new SuccessDataResult<>(
                invoiceDTO,
                Messages.INVOICE_FOUND
        );
    }

    @Cacheable(value = Caches.INVOICES_CACHE, key = "'all'", unless = "#result.success != true")
    @Override
    public DataResult<List<InvoiceDTO>> findAll() {
        List<Invoice> invoiceList = this.invoiceRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICES_LISTED));

        return new SuccessDataResult<>(
                InvoiceMapper.INSTANCE.convertInvoiceDTOs(invoiceList),
                Messages.INVOICES_LISTED
        );
    }

    @Cacheable(value = Caches.INVOICE_USER_CACHE, key = "#userId", unless = "#result.success != true")
    @Override
    public DataResult<UserResponse> findInvoiceUserByUserId(Long userId) {
        ResponseEntity<RestResponse<UserResponse>> response = userServiceClient.findById(userId);

        UserResponse userResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.INVOICE_USER_FOUND));

        return new SuccessDataResult<>(
                userResponse,
                Messages.INVOICE_USER_FOUND
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName("invoice-service")
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
