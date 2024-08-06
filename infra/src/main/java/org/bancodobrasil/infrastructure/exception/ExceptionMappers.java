package org.bancodobrasil.infrastructure.exception;

import jakarta.ws.rs.core.Response;

import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {

    private static final Logger log = Logger.getLogger(ExceptionMappers.class);

    @ServerExceptionMapper(value = {FieldNotValidException.class, FieldRequiredException.class})
    public RestResponse<Exception> handleBadRequestException(Exception e) {
        log.error(e);
        return RestResponse.status(Response.Status.BAD_REQUEST, e);
    }

    @ServerExceptionMapper(value = DataNotFoundException.class)
    public RestResponse<Exception> handleNotFoundException(DataNotFoundException e) {
        log.error(e);
        return RestResponse.status(Response.Status.NOT_FOUND, e);
    }

}
