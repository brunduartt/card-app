package org.bancodobrasil.infrastructure.exception;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {

    private static final Logger log = Logger.getLogger(ExceptionMappers.class);

    @ServerExceptionMapper
    public RestResponse<Exception> handleFieldNotValidException(FieldNotValidException exception) {
        log.error(exception);
        return RestResponse.status(Response.Status.BAD_REQUEST, new ValidationException(exception));
    }

}
