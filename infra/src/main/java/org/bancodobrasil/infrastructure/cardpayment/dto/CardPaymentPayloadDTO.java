package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CardPaymentPayloadDTO {
    @NotNull
    @Positive
    BigDecimal value;

    @NotNull
    @Min(1)
    Integer installments;

    @NotNull
    CardPayloadDTO card;
}
