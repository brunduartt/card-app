package org.bancodobrasil.infrastructure.i18n;

public class I18NEnUSInfra implements I18nInfra {
    @Override
    public String messageDataNotFound(String entity) {
        return String.format("Not found: %s.", entity);
    }

    @Override
    public String cardPayment() {
        return "Card Payment";
    }

    @Override
    public String messageInternalErrorException(String message) {
        return String.format("An error has occurred: %s", message);
    }
}
