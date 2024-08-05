package org.bancodobrasil.core.cardpayment.usecase;

import lombok.RequiredArgsConstructor;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;

import java.util.UUID;

@RequiredArgsConstructor
public class FindCardPaymentByIdUseCaseImpl implements FindCardPaymentByIdUseCase {

    private final CardPaymentService service;

    @Override
    public CardPaymentDomain execute(UUID id) throws Exception {
        return service.findById(id);
    }
}
