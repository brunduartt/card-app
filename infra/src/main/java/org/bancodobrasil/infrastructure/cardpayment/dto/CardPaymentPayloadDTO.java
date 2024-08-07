package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Card payment creation payload")
public class CardPaymentPayloadDTO {
    @NotNull
    @Positive
    @Schema(description = "Payment value")
    BigDecimal value;

    @NotNull
    @Min(1)
    @Schema(description = "Installments amount")
    Integer installments;

    @NotNull
    @Schema(description = "Card information")
    CardPayloadDTO card;
}
