package smart.logservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import smart.logservice.model.Log;
import smart.logservice.model.dto.LogDTO;
import smart.logservice.model.request.LogSaveRequest;
import smart.logservice.model.request.LogUpdateRequest;
import smart.logservice.utils.RabbitMQ.response.LogResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    Log convertToSaveLog(LogSaveRequest logSaveRequest);
    Log convertToUpdateLog(LogUpdateRequest logUpdateRequest);
    LogDTO convertToLogDTO(Log log);
    Log convertToLog(LogResponse logResponse);
    List<LogDTO> convertLogDTOs(List<Log> logList);
}
