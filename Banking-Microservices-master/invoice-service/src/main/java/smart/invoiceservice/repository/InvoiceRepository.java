package smart.invoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.invoiceservice.entity.Invoice;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
