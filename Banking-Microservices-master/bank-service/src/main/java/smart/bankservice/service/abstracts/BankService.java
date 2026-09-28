package smart.bankservice.service.abstracts;

import smart.bankservice.entity.dto.BankDTO;
import smart.bankservice.entity.request.BankSaveRequest;
import smart.bankservice.entity.request.BankUpdateRequest;
import smart.bankservice.utils.result.DataResult;
import smart.bankservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface BankService {
    DataResult<BankDTO> save(BankSaveRequest bankSaveRequest);
    DataResult<BankDTO> update(BankUpdateRequest bankUpdateRequest);
    Result deleteById(Long bankId);
    DataResult<BankDTO> findById(Long bankId);
    DataResult<List<BankDTO>> findAll();
}
