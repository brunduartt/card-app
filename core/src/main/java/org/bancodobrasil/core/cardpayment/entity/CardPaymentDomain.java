package org.bancodobrasil.core.cardpayment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardPaymentDomain {
    private UUID id;
    private BigDecimal value;
    private Integer installments;
    private CardDomain card;
}
