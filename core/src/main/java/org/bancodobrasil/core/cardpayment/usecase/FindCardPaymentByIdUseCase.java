package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;

import java.util.UUID;

public interface FindCardPaymentByIdUseCase {
    /**
     * Finds a CardPayment by id
     * @param id CardPayment id
     * @return CardPaymentDomain
     * @throws Exception CardPayment not found
     * @throws org.bancodobrasil.core.cardpayment.exception.FieldNotValidException ID null
     */
    CardPaymentDomain execute(UUID id) throws Exception;
}
