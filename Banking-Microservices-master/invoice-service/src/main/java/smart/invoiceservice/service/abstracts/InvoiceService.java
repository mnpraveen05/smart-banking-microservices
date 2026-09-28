package smart.invoiceservice.service.abstracts;

import smart.invoiceservice.entity.dto.InvoiceDTO;
import smart.invoiceservice.entity.request.InvoiceSaveRequest;
import smart.invoiceservice.entity.request.InvoiceUpdateRequest;
import smart.invoiceservice.utils.client.dto.UserResponse;
import smart.invoiceservice.utils.result.DataResult;
import smart.invoiceservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface InvoiceService {
    DataResult<InvoiceDTO> save(InvoiceSaveRequest invoiceSaveRequest);
    DataResult<InvoiceDTO> update(InvoiceUpdateRequest invoiceUpdateRequest);
    Result deleteById(Long invoiceId);
    DataResult<InvoiceDTO> findById(Long invoiceId);
    DataResult<List<InvoiceDTO>> findAll();
    DataResult<UserResponse> findInvoiceUserByUserId(Long userId);
}
