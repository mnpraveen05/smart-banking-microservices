package smart.logservice.model.dto;

import smart.logservice.model.enums.LogType;
import smart.logservice.model.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public record LogDTO (
        String id,
        String serviceName,
        OperationType operationType,
        LogType logType,
        String message,
        LocalDateTime timestamp,
        String exception
){
}