package smart.creditcardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.creditcardservice.entity.CreditCard;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
