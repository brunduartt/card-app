package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
    String lastFourDigits;
    String cardHolderName;
    Integer expirationMonth;
    Integer expirationYear;
}
