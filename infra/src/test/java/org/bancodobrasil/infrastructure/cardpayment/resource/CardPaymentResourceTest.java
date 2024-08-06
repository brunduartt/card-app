package org.bancodobrasil.infrastructure.cardpayment.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import jakarta.transaction.Transactional;
import org.bancodobrasil.core.cardpayment.entity.CardDomain;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.usecase.CreateCardPaymentUseCase;
import org.bancodobrasil.core.cardpayment.usecase.FindCardPaymentByIdUseCase;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPayloadDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentDTO;
import org.bancodobrasil.infrastructure.cardpayment.dto.CardPaymentPayloadDTO;
import org.bancodobrasil.infrastructure.cardpayment.persistence.CardPaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@QuarkusTest
@TestHTTPEndpoint(CardPaymentResource.class)
class CardPaymentResourceTest {
    protected static final String TEST_VALID_CARD_NUMBER = "5107032614583346";
    protected static final String TEST_LAST_FOUR_DIGITS = "3346";

    @BeforeEach
    void setUp() {}

    @AfterEach
    @Transactional
    void tearDown() {
        CardPaymentRepository cardPaymentRepository = new CardPaymentRepository();
        cardPaymentRepository.deleteAll();
    }
/*
    @Test
    void shouldGetCardPayment() throws Exception {
        UUID uuid = UUID.fromString("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251");
        doReturn(createMockCardPaymentOutput())
                .when(findCardPaymentByIdUseCase)
                .execute(uuid);

        given().when()
                .get(uuid.toString())
                .then()
                .statusCode(200)
                .body("id", is("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251"));

    }

    @Test
    void shouldCreateCardPayment() throws Exception {

        doReturn(createMockCardPaymentOutput())
                .when(createCardPaymentUseCase)
                .execute(Mockito.any());

        given()
                .contentType(ContentType.JSON)
                .body(createMockCardPaymentPayload())
                .when()
                    .post()
                .then()
                    .statusCode(200)
                .body("id", is("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251"));
    }*/

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

    CardPaymentDomain createMockCardPaymentOutput() {
        CardPaymentDomain cardPayment = new CardPaymentDomain();
        cardPayment.setId(UUID.fromString("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251"));
        cardPayment.setValue(BigDecimal.valueOf(23.40));
        cardPayment.setInstallments(1);

        CardDomain card = new CardDomain();
        card.setCardHolderName("BRUNO D P ASSIS");
        card.setLastFourDigits(TEST_LAST_FOUR_DIGITS);
        card.setExpirationMonth(1);
        card.setExpirationYear(2029);
        cardPayment.setCard(card);

        return cardPayment;
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