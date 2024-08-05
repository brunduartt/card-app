package org.bancodobrasil.infrastructure.exception;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<Exception> handleFieldNotValidException(FieldNotValidException exception) {
        exception.printStackTrace();
        return RestResponse.status(Response.Status.BAD_REQUEST, new ValidationException(exception));
    }

    @ServerExceptionMapper
    public RestResponse<Exception> handleException(Exception exception) {
        exception.printStackTrace();
        return RestResponse.serverError();
    }
}
