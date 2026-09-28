package smart.accountservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import smart.accountservice.entity.Account;
import smart.accountservice.entity.dto.AccountDTO;
import smart.accountservice.entity.request.AccountSaveRequest;
import smart.accountservice.entity.request.AccountUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account convertToSaveAccount(AccountSaveRequest accountSaveRequest);
    Account convertToUpdateAccount(AccountUpdateRequest accountUpdateRequest);
    AccountDTO convertToAccountDTO(Account account);
    List<AccountDTO> convertAccountDTOs(List<Account> accountList);
}
