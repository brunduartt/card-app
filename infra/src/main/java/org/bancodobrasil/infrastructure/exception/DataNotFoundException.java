package org.bancodobrasil.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bancodobrasil.infrastructure.i18n.I18nInfra;

/**
 * Data not found exception
 * Thrown when can't find an entity
 */
@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class DataNotFoundException extends Exception {
    public DataNotFoundException(I18nInfra i18n, String field) {
        super(i18n.messageDataNotFound(field));
    }
}
