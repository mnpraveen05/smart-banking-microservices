package smart.logservice.service.abstracts;

import smart.logservice.model.dto.LogDTO;
import smart.logservice.model.request.LogSaveRequest;
import smart.logservice.model.request.LogUpdateRequest;
import smart.logservice.utils.result.DataResult;
import smart.logservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface LogService {
    DataResult<LogDTO> save(LogSaveRequest logSaveRequest);
    DataResult<LogDTO> update(LogUpdateRequest logUpdateRequest);
    Result deleteById(String logId);
    DataResult<LogDTO> findById(String logId);
    DataResult<List<LogDTO>> findAll();
}
