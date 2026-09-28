package smart.creditcardservice.service.abstracts;


import smart.creditcardservice.entity.dto.CreditCardDTO;
import smart.creditcardservice.entity.request.CreditCardSaveRequest;
import smart.creditcardservice.entity.request.CreditCardUpdateRequest;
import smart.creditcardservice.utils.client.dto.BankResponse;
import smart.creditcardservice.utils.client.dto.UserResponse;
import smart.creditcardservice.utils.result.DataResult;
import smart.creditcardservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface CreditCardService {
    DataResult<CreditCardDTO> save(CreditCardSaveRequest creditCardSaveRequest);
    DataResult<CreditCardDTO> update(CreditCardUpdateRequest creditCardUpdateRequest);
    Result deleteById(Long creditCardId);
    DataResult<CreditCardDTO> findById(Long creditCardId);
    DataResult<List<CreditCardDTO>> findAll();
    DataResult<UserResponse> findCreditCardUserByUserId(Long userId);
    DataResult<BankResponse> findCreditCardBankByBankId(Long bankId);
}
