package org.bancodobrasil.infrastructure.cardpayment.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Card information payload used for card payment creation")
public class CardPayloadDTO {
    @NotBlank
    @Schema(description = "Card number")
    String cardNumber;

    @NotBlank
    @Schema(description = "Card verification code")
    String cvc;

    @NotBlank
    @Schema(description = "Card holder name")
    String cardHolderName;

    @NotNull
    @Min(1)
    @Schema(description = "Card expiration month")
    Integer expirationMonth;

    @NotNull
    @Positive
    @Schema(description = "Card expiration year")
    Integer expirationYear;
}
