package org.bancodobrasil.infrastructure.i18n;

/**
 * I18n interface for each language implementation
 */
public interface I18nInfra {
    String messageDataNotFound(String entity);
    String cardPayment();
    String messageInternalErrorException(String message);
}
