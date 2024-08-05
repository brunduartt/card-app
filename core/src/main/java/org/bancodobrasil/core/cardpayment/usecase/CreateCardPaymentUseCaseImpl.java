package org.bancodobrasil.core.cardpayment.usecase;

import lombok.RequiredArgsConstructor;
import org.apache.maven.shared.utils.StringUtils;
import org.bancodobrasil.core.cardpayment.entity.CardDomain;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
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
        this.validateFields(domain);
        return service.create(domain);
    }

    private void validateFields(CardPaymentDomain cardPaymentDomain) throws Exception {
        if(cardPaymentDomain.getValue() == null) {
            throw new FieldNotValidException("Payment value required");
        }
        if(cardPaymentDomain.getValue().compareTo(BigDecimal.ZERO) < 1) {
            throw new FieldNotValidException("Payment value cannot be 0 or negative");
        }
        if(cardPaymentDomain.getInstallments() < 1) {
            throw new FieldNotValidException("Installments cannot be 0 or negative");
        }
        if(cardPaymentDomain.getCard() == null) {
            throw new FieldNotValidException("Card data required");
        }

        validateCardFields(cardPaymentDomain.getCard());

    }

    private void validateCardFields(CardDomain cardDomain) throws Exception {
        if(StringUtils.isBlank(cardDomain.getCardHolderName())) {
            throw new FieldNotValidException("Card holder name required.");
        }
        if(StringUtils.isBlank(cardDomain.getCardNumber())) {
            throw new FieldNotValidException("Card number required.");
        }
        if(!ValidationUtils.isCreditCardNumberValid(cardDomain.getCardNumber())){
            throw new FieldNotValidException("Card number invalid.");
        }
        if(cardDomain.getExpirationYear() == null) {
            throw new FieldNotValidException("Card expiration year required.");
        }
        if(cardDomain.getExpirationYear() < 1) {
            throw new FieldNotValidException("Card expiration year cannot be 0 or negative.");
        }
        if(cardDomain.getExpirationMonth() == null) {
            throw new FieldNotValidException("Card expiration month required.");
        }
        if(cardDomain.getExpirationMonth() < 1) {
            throw new FieldNotValidException("Card expiration month cannot be 0 or negative.");
        }
        validateExpirationDate(cardDomain.getExpirationMonth(), cardDomain.getExpirationYear());
    }

    private void validateExpirationDate(int month, int year) throws Exception {
        YearMonth expirationDate = YearMonth.parse(
                String.format("%02d",month) + "/"+ year ,
                DateTimeFormatter.ofPattern( "MM/uuuu" )
        );
        if(expirationDate.isBefore(YearMonth.now())) {
            throw new FieldNotValidException("Card expired.");
        }
    }
}
