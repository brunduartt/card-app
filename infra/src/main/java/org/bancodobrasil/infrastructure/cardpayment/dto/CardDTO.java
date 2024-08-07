package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Card output data")
public class CardDTO {
    @Schema(description = "Card number last 4 digits")
    String lastFourDigits;
    @Schema(description = "Card holder name")
    String cardHolderName;
    @Schema(description = "Card expiration month")
    Integer expirationMonth;
    @Schema(description = "Card expiration year")
    Integer expirationYear;
}
