package org.bancodobrasil.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bancodobrasil.infrastructure.i18n.I18nInfra;

/**
 * Internal error exception
 * Thrown when throws unexpected exception or client request exceptions
 */
@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class InternalErrorException extends Exception {
    public InternalErrorException(I18nInfra i18n, String message) {
        super(i18n.messageInternalErrorException(message));
    }
}
