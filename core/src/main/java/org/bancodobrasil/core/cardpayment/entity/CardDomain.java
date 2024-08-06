package org.bancodobrasil.core.cardpayment.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
public class CardDomain {

    String cardNumber;
    String lastFourDigits;
    String cardHolderName;
    Integer expirationMonth;
    Integer expirationYear;
}
