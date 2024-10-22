package org.bancodobrasil.infrastructure.i18n;

public class I18NPtBRInfra implements I18nInfra {
    @Override
    public String messageDataNotFound(String entity) {
        return String.format("Não encontrado: %s.", entity);
    }

    @Override
    public String cardPayment() {
        return "Pagamento (Cartão)";
    }

    @Override
    public String messageInternalErrorException(String message) {
        return String.format("Ocorreu um erro: %s", message);
    }
}
