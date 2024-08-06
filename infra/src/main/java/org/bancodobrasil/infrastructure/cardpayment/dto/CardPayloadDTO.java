package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CardPayloadDTO {
    @NotBlank
    String cardNumber;

    @NotBlank
    String cvc;

    @NotBlank
    String cardHolderName;

    @NotNull
    @Min(1)
    Integer expirationMonth;

    @NotNull
    @Positive
    Integer expirationYear;
}
