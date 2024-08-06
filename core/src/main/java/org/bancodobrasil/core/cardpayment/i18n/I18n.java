package org.bancodobrasil.core.cardpayment.i18n;

public interface I18n {

    String messageMustBePositive();
    String messageFieldRequired(String field);
    String messageFieldInvalid(String field, String message);
    String messageMustBeBetween(int min, int max);
    String messageCardExpired();
}
