package org.bancodobrasil.core.cardpayment.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class FieldNotValidException extends Exception {
    public FieldNotValidException(String message) {
        super("Field not valid: " + message);
    }
}
