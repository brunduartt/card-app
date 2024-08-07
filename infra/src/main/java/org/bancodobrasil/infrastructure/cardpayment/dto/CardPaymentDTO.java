package org.bancodobrasil.infrastructure.cardpayment.dto;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Card payment output data")
public class CardPaymentDTO {
    UUID id;
    @Schema(description = "Payment value")
    BigDecimal value;
    @Schema(description = "Installments amount")
    Integer installments;
    @Schema(description = "Card information")
    CardDTO card;
}
