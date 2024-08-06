package org.bancodobrasil.infrastructure.cardpayment.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Card {
    @Column(name="last_four_digits", nullable = false, length = 4)
    String lastFourDigits;

    @Column(name="card_holder_name", nullable = false)
    String cardHolderName;

    @Size(min = 1, max=12)
    @Column(name="expiration_month", nullable = false)
    Integer expirationMonth;

    @Column(name="expiration_year", nullable = false)
    Integer expirationYear;
}
