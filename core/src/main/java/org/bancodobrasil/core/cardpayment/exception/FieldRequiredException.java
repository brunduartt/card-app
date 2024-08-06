package org.bancodobrasil.core.cardpayment.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bancodobrasil.core.cardpayment.i18n.I18n;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class FieldRequiredException extends Exception {
    public FieldRequiredException(I18n i18n, String field) {
        super(i18n.messageFieldRequired(field));
    }
}
