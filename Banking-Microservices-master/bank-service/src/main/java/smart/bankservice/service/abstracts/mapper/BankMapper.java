package smart.bankservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import smart.bankservice.entity.Bank;
import smart.bankservice.entity.dto.BankDTO;
import smart.bankservice.entity.request.BankSaveRequest;
import smart.bankservice.entity.request.BankUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    Bank convertToSaveBank(BankSaveRequest bankSaveRequest);
    Bank convertToUpdateBank(BankUpdateRequest bankUpdateRequest);
    BankDTO convertToBankDTO(Bank bank);
    List<BankDTO> convertBankDTOs(List<Bank> bankList);
}
