package smart.invoiceservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import smart.invoiceservice.entity.Invoice;
import smart.invoiceservice.entity.dto.InvoiceDTO;
import smart.invoiceservice.entity.request.InvoiceSaveRequest;
import smart.invoiceservice.entity.request.InvoiceUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    Invoice convertToSaveInvoice(InvoiceSaveRequest invoiceSaveRequest);
    Invoice convertToUpdateInvoice(InvoiceUpdateRequest invoiceUpdateRequest);
    InvoiceDTO convertToInvoiceDTO(Invoice invoice);
    List<InvoiceDTO> convertInvoiceDTOs(List<Invoice> invoiceList);

}
