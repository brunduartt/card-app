package org.bancodobrasil.infrastructure.cardpayment.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;
import org.bancodobrasil.infrastructure.cardpayment.mapper.CardPaymentMapper;
import org.bancodobrasil.infrastructure.cardpayment.persistence.CardPayment;
import org.bancodobrasil.infrastructure.cardpayment.persistence.CardPaymentRepository;
import org.bancodobrasil.infrastructure.exception.DataNotFoundException;
import org.bancodobrasil.infrastructure.i18n.I18nFactoryInfra;
import org.bancodobrasil.infrastructure.i18n.I18nInfra;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class CardPaymentServiceImpl implements CardPaymentService {

    @Inject
    CardPaymentRepository cardPaymentRepository;

    @Inject
    CardPaymentMapper cardPaymentMapper;

    @Override
    public CardPaymentDomain findById(UUID id) throws Exception {
        I18nInfra i18n = I18nFactoryInfra.get();
        Optional<CardPayment> cardPayment = cardPaymentRepository.findByIdOptional(id);
        if(cardPayment.isEmpty()) {
            throw new DataNotFoundException(i18n, i18n.cardPayment());
        }
        return cardPaymentMapper.toDomain(cardPayment.get());
    }

    @Override
    public CardPaymentDomain create(CardPaymentDomain domain) {

        String cardNumber = domain.getCard().getCardNumber();
        domain.getCard().setLastFourDigits(cardNumber.substring(cardNumber.length() - 4));

        CardPayment cardPayment = cardPaymentMapper.toEntity(domain);
        cardPaymentRepository.persist(cardPayment);
        return cardPaymentMapper.toDomain(cardPayment);
    }
}
