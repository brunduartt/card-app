package org.bancodobrasil.core.cardpayment.usecase;

import lombok.RequiredArgsConstructor;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.bancodobrasil.core.cardpayment.i18n.I18n;
import org.bancodobrasil.core.cardpayment.i18n.I18nFactory;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;

import java.util.UUID;

@RequiredArgsConstructor
public class FindCardPaymentByIdUseCaseImpl implements FindCardPaymentByIdUseCase {

    private final CardPaymentService service;

    @Override
    public CardPaymentDomain execute(UUID id) throws Exception {
        I18n i18n = I18nFactory.get();
        if(id == null) {
            throw new FieldRequiredException(i18n, CardPaymentDomain.Fields.id);
        }
        return service.findById(id);
    }
}
