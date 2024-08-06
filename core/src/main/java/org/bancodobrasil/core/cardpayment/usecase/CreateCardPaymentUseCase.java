package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;

public interface CreateCardPaymentUseCase {
    /**
     * Creates a new CardPayment and returns it
     * @param domain CardPayment to be created
     * @return new CardPaymentDomain with assigned id
     * @throws org.bancodobrasil.core.cardpayment.exception.FieldNotValidException Invalid parameters
     */
    CardPaymentDomain execute(CardPaymentDomain domain) throws Exception;
}
