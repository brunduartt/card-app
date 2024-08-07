package org.bancodobrasil.core.cardpayment.i18n;

public class I18nEnUS implements I18n {
    @Override
    public String messageMustBePositive() {
        return String.format("Must be positive");
    }

    @Override
    public String messageFieldRequired(String field) {
        return String.format("Field required: %s.", field);
    }

    @Override
    public String messageFieldInvalid(String field, String message) {
        return String.format("Field invalid: %s. %s", field, message);
    }

    @Override
    public String messageMustBeBetween(int min, int max) {
        return String.format("Must be between %d and %d", min, max);
    }

    @Override
    public String messageCardExpired() {
        return "Card expired";
    }
}
