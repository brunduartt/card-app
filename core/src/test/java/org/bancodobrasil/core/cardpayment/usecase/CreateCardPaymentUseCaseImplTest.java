package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.entity.CardDomain;
import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;
import org.bancodobrasil.core.cardpayment.exception.FieldNotValidException;
import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
class CreateCardPaymentUseCaseImplTest {
    private static final String TEST_VALID_CARD_NUMBER = "5107032614583346";

    @InjectMocks
    CardPaymentService cardPaymentService;

    CreateCardPaymentUseCase createCardPaymentUseCase;
    @BeforeEach
    void setUp() {
        createCardPaymentUseCase = new CreateCardPaymentUseCaseImpl(cardPaymentService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowIfValueInvalid() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        cardPaymentDomain.setValue(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.setValue(BigDecimal.ZERO);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.setValue(BigDecimal.valueOf(-1));
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    @Test
    void shouldThrowIfInstallmentInvalid() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        cardPaymentDomain.setInstallments(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.setInstallments(0);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.setInstallments(-1);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    @Test
    void shouldThrowIfCardIsNull() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        cardPaymentDomain.setCard(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }


    @Test
    void shouldThrowIfCardNumberInvalid() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
        cardPaymentDomain.getCard().setCardNumber("234324");
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    @Test
    void shoudThrowIfExpirationYearIsInvalid() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        cardPaymentDomain.getCard().setExpirationYear(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.getCard().setExpirationYear(2023);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.getCard().setExpirationYear(10000);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    @Test
    void shoudThrowIfExpirationMonthIsInvalid() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        cardPaymentDomain.getCard().setExpirationMonth(null);
        assertThrows(FieldRequiredException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.getCard().setExpirationMonth(13);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));

        cardPaymentDomain.getCard().setExpirationMonth(0);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    @Test
    void shoudThrowIfExpirationDateIsInThePast() {
        CardPaymentDomain cardPaymentDomain = createMockCardPayment(TEST_VALID_CARD_NUMBER);
        YearMonth yearMonth = YearMonth.now();
        cardPaymentDomain.getCard().setExpirationYear(yearMonth.getYear());
        cardPaymentDomain.getCard().setExpirationMonth(yearMonth.getMonth().getValue() - 1);
        assertThrows(FieldNotValidException.class, () -> createCardPaymentUseCase.execute(cardPaymentDomain));
    }

    CardPaymentDomain createMockCardPayment(String cardNumber) {
        CardPaymentDomain cardPayment = new CardPaymentDomain();
        cardPayment.setId(UUID.fromString("7c8fb3b2-6a4e-42a8-8056-f9d5bae85251"));
        cardPayment.setValue(BigDecimal.valueOf(23.40));
        cardPayment.setInstallments(1);

        CardDomain card = new CardDomain();
        card.setCardHolderName("BRUNO D P ASSIS");
        card.setCardNumber(cardNumber);
        card.setExpirationMonth(1);
        card.setExpirationYear(2029);
        cardPayment.setCard(card);

        return cardPayment;
    }
}