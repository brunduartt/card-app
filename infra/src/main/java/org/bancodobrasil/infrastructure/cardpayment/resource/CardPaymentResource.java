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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.UUID;
@Tag(name = "Card Payment")
@Path("/card-payment")
public class CardPaymentResource {

    private static final Logger LOG = Logger.getLogger(CardPaymentResource.class);

    @Inject
    FindCardPaymentByIdUseCase findCardPaymentByIdUseCase;

    @Inject
    CreateCardPaymentUseCase createCardPaymentUseCase;

    @Inject
    CardPaymentMapper mapper;

    @Operation(
            summary = "Find and returns a card payment by id"
    )
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK"),
            @APIResponse(responseCode = "400", description = "Id null"),
            @APIResponse(responseCode = "404", description = "Card payment not found"),
            @APIResponse(responseCode = "500", description = "Unexpected error")
    })
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<CardPaymentDTO> getCardPayment(@Parameter(required = true, description = "CardPayment id")
                                                           @PathParam("id") @NotNull UUID id) throws Exception {
        return RestResponse.ok(mapper.toDTO(findCardPaymentByIdUseCase.execute(id)));
    }


    @Operation(
            summary = "Creates a card payment"
    )
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Card payment was created successfully"),
            @APIResponse(responseCode = "400", description = "Field invalid or required"),
            @APIResponse(responseCode = "500", description = "Unexpected error")
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<CardPaymentDTO> createCardPayment(@RequestBody(required = true) @Valid @NotNull CardPaymentPayloadDTO payload) throws Exception {
        return RestResponse.ok(mapper.toDTO(createCardPaymentUseCase.execute(mapper.toDomain(payload))));
    }
}
