package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CardPayloadDTO {
    String cardNumber;
    String cvc;
    String cardHolderName;
    Integer expirationMonth;
    Integer expirationYear;
}
