package org.bancodobrasil.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bancodobrasil.infrastructure.i18n.I18nInfra;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class DataNotFoundException extends Exception {
    public DataNotFoundException(I18nInfra i18n, String field) {
        super(i18n.messageDataNotFound(field));
    }
}
