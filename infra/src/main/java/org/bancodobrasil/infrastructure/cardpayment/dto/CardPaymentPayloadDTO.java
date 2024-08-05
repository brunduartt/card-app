package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardPaymentPayloadDTO {
    BigDecimal value;
    Integer installments;
    CardPayloadDTO card;
}
