package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;

import java.util.UUID;

public interface FindCardPaymentByIdUseCase {
    CardPaymentDomain execute(UUID id) throws Exception;
}
