package org.bancodobrasil.infrastructure.cardpayment.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "card_payment")
public class CardPayment {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Min(1)
    @Column(name = "installments", nullable = false)
    private Integer installments;

    @Embedded
    private Card card;
}
