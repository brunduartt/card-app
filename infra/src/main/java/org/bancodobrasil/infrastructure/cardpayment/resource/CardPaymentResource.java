package org.bancodobrasil.infrastructure.cardpayment.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bancodobrasil.core.cardpayment.usecase.CreateCardPaymentUseCase;
import org.bancodobrasil.core.cardpayment.usecase.FindCardPaymentByIdUseCase;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentPayloadDTO;
import org.bancodobrasil.infrastructure.cardpayment.mapper.CardPaymentMapper;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.UUID;

@Path("/card-payment")
public class CardPaymentResource {

    @Inject
    FindCardPaymentByIdUseCase findCardPaymentByIdUseCase;

    @Inject
    CreateCardPaymentUseCase createCardPaymentUseCase;

    @Inject
    CardPaymentMapper mapper;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<CardPaymentDTO> getCardPayment(@PathParam("id") @NotNull UUID id) throws Exception {
        return RestResponse.ok(mapper.toDTO(findCardPaymentByIdUseCase.execute(id)));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<CardPaymentDTO> createCardPayment(@Valid @NotNull CardPaymentPayloadDTO payload) throws Exception {
        return RestResponse.ok(mapper.toDTO(createCardPaymentUseCase.execute(mapper.toDomain(payload))));
    }
}
