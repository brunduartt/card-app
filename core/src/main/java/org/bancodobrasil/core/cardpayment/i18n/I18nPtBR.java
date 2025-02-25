package org.bancodobrasil.core.cardpayment.i18n;

public class I18nPtBR implements I18n {
    @Override
    public String messageMustBePositive() {
        return String.format("Deve ser positivo");
    }

    @Override
    public String messageFieldRequired(String field) {
        return String.format("Campo obrigatório: %s.", field);
    }

    @Override
    public String messageFieldInvalid(String field, String message) {
        return String.format("Campo inválido: %s. %s", field, message);
    }

    @Override
    public String messageMustBeBetween(int min, int max) {
        return String.format("Deve estar entre %d e %d", min, max);
    }

    @Override
    public String messageCardExpired() {
        return "Cartão vencido";
    }
}
