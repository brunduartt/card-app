package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;

import java.util.UUID;

public interface CreateCardPaymentUseCase {
    CardPaymentDomain execute(CardPaymentDomain domain) throws Exception;
}
