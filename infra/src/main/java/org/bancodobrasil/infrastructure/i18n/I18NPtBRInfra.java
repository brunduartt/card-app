package org.bancodobrasil.infrastructure.i18n;

public class I18NPtBRInfra implements I18nInfra {
    @Override
    public String messageDataNotFound(String entity) {
        return String.format("Not found: %s.", entity);
    }

    @Override
    public String cardPayment() {
        return "Card Payment";
    }
}
