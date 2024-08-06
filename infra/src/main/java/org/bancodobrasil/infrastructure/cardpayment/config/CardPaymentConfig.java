package org.bancodobrasil.infrastructure.cardpayment.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;
import org.bancodobrasil.core.cardpayment.usecase.CreateCardPaymentUseCase;
import org.bancodobrasil.core.cardpayment.usecase.CreateCardPaymentUseCaseImpl;
import org.bancodobrasil.core.cardpayment.usecase.FindCardPaymentByIdUseCase;
import org.bancodobrasil.core.cardpayment.usecase.FindCardPaymentByIdUseCaseImpl;

public class CardPaymentConfig {

    @ApplicationScoped
    FindCardPaymentByIdUseCase findCardPaymentByIdUseCase(CardPaymentService service) {
        return new FindCardPaymentByIdUseCaseImpl(service);
    }

  @ApplicationScoped
    CreateCardPaymentUseCase createCardPaymentUseCase(CardPaymentService service) {
        return new CreateCardPaymentUseCaseImpl(service);
    }
}
