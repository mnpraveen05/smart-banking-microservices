package smart.invoiceservice.utils.rabbitMQ.request;

import lombok.*;
import smart.invoiceservice.utils.rabbitMQ.enums.LogType;
import smart.invoiceservice.utils.rabbitMQ.enums.OperationType;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LogRequest {
    private String serviceName;
    private OperationType operationType;
    private LogType logType;
    private String message;
    private LocalDateTime timestamp;
    private String exception;
}
