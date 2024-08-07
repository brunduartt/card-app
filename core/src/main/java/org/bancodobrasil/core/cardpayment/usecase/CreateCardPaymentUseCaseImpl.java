package org.bancodobrasil.core.cardpayment.usecase;

import lombok.RequiredArgsConstructor;
import org.apache.maven.shared.utils.StringUtils;
import org.bancodobrasil.core.cardpayment.entity.CardDomain;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.bancodobrasil.core.cardpayment.i18n.I18n;
import org.bancodobrasil.core.cardpayment.i18n.I18nFactory;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;
import org.bancodobrasil.core.cardpayment.utils.ValidationUtils;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class CreateCardPaymentUseCaseImpl implements CreateCardPaymentUseCase {

    private final CardPaymentService service;

    @Override
    public CardPaymentDomain execute(CardPaymentDomain domain) throws Exception {
        I18n i18n = I18nFactory.getDefault();
        this.validateFields(i18n, domain);
        return service.create(domain);
    }

    private void validateFields(I18n i18n, CardPaymentDomain cardPaymentDomain) throws Exception {
        if(cardPaymentDomain.getValue() == null) {
            throw new FieldRequiredException(i18n, CardPaymentDomain.Fields.value);
        }
        if(cardPaymentDomain.getValue().compareTo(BigDecimal.ZERO) < 1) {
            throw new FieldNotValidException(i18n, CardPaymentDomain.Fields.value, i18n.messageMustBePositive());
        }
        if(cardPaymentDomain.getInstallments() == null) {
            throw new FieldRequiredException(i18n, CardPaymentDomain.Fields.installments);
        }
        if(cardPaymentDomain.getInstallments() < 1) {
            throw new FieldNotValidException(i18n, CardPaymentDomain.Fields.installments, i18n.messageMustBePositive());
        }
        if(cardPaymentDomain.getCard() == null) {
            throw new FieldRequiredException(i18n, CardPaymentDomain.Fields.card);
        }

        validateCardFields(i18n, cardPaymentDomain.getCard());

    }

    private void validateCardFields(I18n i18n, CardDomain cardDomain) throws Exception {
        if(StringUtils.isBlank(cardDomain.getCardHolderName())) {
            throw new FieldRequiredException(i18n, CardDomain.Fields.cardHolderName);
        }
        if(StringUtils.isBlank(cardDomain.getCardNumber())) {
            throw new FieldRequiredException(i18n, CardDomain.Fields.cardNumber);
        }
        if(!ValidationUtils.isCreditCardNumberValid(cardDomain.getCardNumber())) {
            throw new FieldNotValidException(i18n, CardDomain.Fields.cardNumber);
        }
        validateExpirationDate(i18n, cardDomain.getExpirationMonth(), cardDomain.getExpirationYear());
    }

    private void validateExpirationDate(I18n i18n, Integer month, Integer year) throws Exception {
        if(month == null) {
            throw new FieldRequiredException(i18n, CardDomain.Fields.expirationMonth);
        }
        if(month < 1 || month > 12) {
            throw new FieldNotValidException(i18n, CardDomain.Fields.expirationMonth, i18n.messageMustBeBetween(1, 12));
        }

        if(year == null) {
            throw new FieldRequiredException(i18n, CardDomain.Fields.expirationYear);
        }
        YearMonth now = YearMonth.now();
        if(year < now.getYear() || year > 9999) {
            throw new FieldNotValidException(i18n, CardDomain.Fields.expirationYear);
        }

        YearMonth expirationDate = YearMonth.parse(
                String.format("%02d",month) + "/"+ year ,
                DateTimeFormatter.ofPattern( "MM/uuuu" )
        );
        if(expirationDate.isBefore(now)) {
            throw new FieldNotValidException(i18n, CardPaymentDomain.Fields.card, i18n.messageCardExpired());
        }
    }
}
