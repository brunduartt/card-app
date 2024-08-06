package org.bancodobrasil.infrastructure.cardpayment.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPayloadDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentPayloadDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusIntegrationTest
@TestHTTPEndpoint(CardPaymentResource.class)
class CardPaymentResourceIT extends CardPaymentResourceTest {

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    void shouldGetCardPayment() {
        UUID uuid = UUID.fromString("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251");

        CardPaymentDTO dto = given().when()
                    .get(uuid.toString())
                .then()
                    .statusCode(200)
                .extract()
                    .as(CardPaymentDTO.class);

        assertNotNull(dto);
        assertEquals(dto.getId(), uuid);
    }

    @Test
    void shouldCreateCardPayment() {
        CardPaymentDTO dto = given()
                    .contentType(ContentType.JSON)
                    .body(createMockCardPaymentPayload(TEST_VALID_CARD_NUMBER))
                .when()
                    .post()
                .then()
                    .statusCode(200)
                .extract()
                    .as(CardPaymentDTO.class);

        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertNotNull(dto.getCard());
        assertEquals(dto.getCard().getLastFourDigits(), TEST_LAST_FOUR_DIGITS);
    }

    CardPaymentPayloadDTO createMockCardPaymentPayload(String cardNumber) {
        CardPaymentPayloadDTO cardPayment = new CardPaymentPayloadDTO();
        cardPayment.setValue(BigDecimal.valueOf(23.40));
        cardPayment.setInstallments(1);

        CardPayloadDTO card = new CardPayloadDTO();
        card.setCardHolderName("BRUNO D P ASSIS");
        card.setCardNumber(cardNumber);
        card.setExpirationMonth(1);
        card.setExpirationYear(2029);
        cardPayment.setCard(card);

        return cardPayment;
    }
}