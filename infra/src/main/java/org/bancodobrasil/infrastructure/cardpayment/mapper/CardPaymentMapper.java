package org.bancodobrasil.infrastructure.cardpayment.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.bancodobrasil.core.cardpayment.entity.CardDomain;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentPayloadDTO;
import org.bancodobrasil.infrastructure.cardpayment.persistence.Card;
import org.bancodobrasil.infrastructure.cardpayment.persistence.CardPayment;

@ApplicationScoped
public class CardPaymentMapper {

    public CardPaymentDomain toDomain(CardPayment entity) {
        CardPaymentDomain domain = new CardPaymentDomain();
        domain.setId(entity.getId());
        domain.setValue(entity.getValue());
        domain.setInstallments(entity.getInstallments());

        CardDomain card = new CardDomain();
        card.setCardHolderName(entity.getCard().getCardHolderName());
        card.setLastFourDigits(entity.getCard().getLastFourDigits());
        card.setExpirationMonth(entity.getCard().getExpirationMonth());
        card.setExpirationYear(entity.getCard().getExpirationYear());
        domain.setCard(card);

        return domain;
    }

    public CardPaymentDomain toDomain(CardPaymentPayloadDTO payload) {
        CardPaymentDomain domain = new CardPaymentDomain();
        domain.setValue(payload.getValue());
        domain.setInstallments(payload.getInstallments());

        CardDomain card = new CardDomain();
        card.setCardHolderName(payload.getCard().getCardHolderName());
        String cardNumber = payload.getCard().getCardNumber();
        if(cardNumber != null) {
            card.setCardNumber(cardNumber);
        }
        card.setExpirationMonth(payload.getCard().getExpirationMonth());
        card.setExpirationYear(payload.getCard().getExpirationYear());
        domain.setCard(card);

        return domain;
    }

    public CardPaymentDTO toDTO(CardPaymentDomain domain) {
        CardPaymentDTO dto = new CardPaymentDTO();
        dto.setId(domain.getId());
        dto.setValue(domain.getValue());
        dto.setInstallments(domain.getInstallments());

        CardDTO card = new CardDTO();
        card.setCardHolderName(domain.getCard().getCardHolderName());
        card.setLastFourDigits(domain.getCard().getLastFourDigits());
        card.setExpirationMonth(domain.getCard().getExpirationMonth());
        card.setExpirationYear(domain.getCard().getExpirationYear());
        dto.setCard(card);

        return dto;
    }

    public CardPayment toEntity(CardPaymentDomain domain) {
        CardPayment entity = new CardPayment();
        entity.setId(domain.getId());
        entity.setValue(domain.getValue());
        entity.setInstallments(domain.getInstallments());

        Card card = new Card();
        card.setCardHolderName(domain.getCard().getCardHolderName());
        card.setLastFourDigits(domain.getCard().getLastFourDigits());
        card.setExpirationMonth(domain.getCard().getExpirationMonth());
        card.setExpirationYear(domain.getCard().getExpirationYear());
        entity.setCard(card);

        return entity;
    }
}
