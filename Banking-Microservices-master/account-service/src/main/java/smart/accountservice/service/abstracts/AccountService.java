package smart.accountservice.service.abstracts;

import smart.accountservice.entity.dto.AccountDTO;
import smart.accountservice.entity.request.AccountSaveRequest;
import smart.accountservice.entity.request.AccountUpdateRequest;
import smart.accountservice.utils.client.dto.BankResponse;
import smart.accountservice.utils.client.dto.UserResponse;
import smart.accountservice.utils.result.DataResult;
import smart.accountservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface AccountService {
    DataResult<AccountDTO> save(AccountSaveRequest accountSaveRequest);
    DataResult<AccountDTO> update(AccountUpdateRequest accountUpdateRequest);
    Result deleteById(Long accountId);
    DataResult<AccountDTO> findById(Long accountId);
    DataResult<List<AccountDTO>> findAll();
    DataResult<UserResponse> findAccountUserByUserId(Long userId);
    DataResult<BankResponse> findAccountBankByBankId(Long bankId);
}
