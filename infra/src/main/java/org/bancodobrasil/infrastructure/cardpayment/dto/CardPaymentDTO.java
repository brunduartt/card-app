package org.bancodobrasil.infrastructure.cardpayment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardPaymentDTO {
    UUID id;
    BigDecimal value;
    Integer installments;
    CardDTO card;
}
