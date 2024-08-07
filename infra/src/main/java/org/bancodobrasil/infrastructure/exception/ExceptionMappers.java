package org.bancodobrasil.infrastructure.exception;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.bancodobrasil.infrastructure.i18n.I18nFactoryInfra;
import org.bancodobrasil.infrastructure.i18n.I18nInfra;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
/**
 * Handles and logs each exception and returns a RestResponse
 */
public class ExceptionMappers {

    @Inject
    private MeterRegistry registry;

    private static final Logger log = Logger.getLogger(ExceptionMappers.class);

    /**
     * Field validations exception
     */
    @ServerExceptionMapper(value = {FieldNotValidException.class, FieldRequiredException.class})
    public RestResponse<Exception> handleBadRequestException(Exception e) {
        log.error(e);
        return RestResponse.status(Response.Status.BAD_REQUEST, e);
    }

    /**
     * Data not found exception
     */
    @ServerExceptionMapper(value = DataNotFoundException.class)
    public RestResponse<Exception> handleNotFoundException(DataNotFoundException e) {
        log.error(e);
        return RestResponse.status(Response.Status.NOT_FOUND, e);
    }

    /**
     * HTTP request exceptions
     */
    @ServerExceptionMapper(value = ClientErrorException.class)
    public RestResponse<Exception> handleRequestException(ClientErrorException e) throws Exception {
        log.error(e);
        return RestResponse.status(e.getResponse() != null ? Response.Status.fromStatusCode(e.getResponse().getStatus()) : Response.Status.INTERNAL_SERVER_ERROR,
                new InternalErrorException(I18nFactoryInfra.getDefault(), e.getMessage()));
    }

    /**
     * Not expected exceptions
     */
    @ServerExceptionMapper
    public RestResponse<Exception> handleInternalErrorException(Exception e) throws Exception {
        log.error(e);
        registry.counter("errors.internal-error-exception").increment();
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, new InternalErrorException(I18nFactoryInfra.getDefault(), e.getMessage()));
    }

}
