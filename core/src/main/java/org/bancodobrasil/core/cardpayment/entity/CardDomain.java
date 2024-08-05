package org.bancodobrasil.core.cardpayment.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDomain {
    String cardNumber;
    String lastFourDigits;
    String cardHolderName;
    Integer expirationMonth;
    Integer expirationYear;
}
