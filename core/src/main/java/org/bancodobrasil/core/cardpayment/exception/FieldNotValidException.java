package org.bancodobrasil.core.cardpayment.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bancodobrasil.core.cardpayment.i18n.I18n;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class FieldNotValidException extends Exception {

    public FieldNotValidException(I18n i18n, String field, String message) {
        super(i18n.messageFieldInvalid(field, message));
    }

    public FieldNotValidException(I18n i18n, String field) {
        super(i18n.messageFieldInvalid(field, ""));
    }
}
