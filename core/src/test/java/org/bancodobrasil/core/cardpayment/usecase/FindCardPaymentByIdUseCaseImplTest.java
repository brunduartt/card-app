package org.bancodobrasil.core.cardpayment.usecase;

import org.bancodobrasil.core.cardpayment.exception.FieldRequiredException;
import org.bancodobrasil.core.cardpayment.ports.CardPaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class FindCardPaymentByIdUseCaseImplTest {
    private static final String TEST_VALID_CARD_NUMBER = "5107032614583346";
    private static final String TEST_LAST_FOUR_DIGITS = "3346";

    @InjectMocks
    CardPaymentService cardPaymentService;

    FindCardPaymentByIdUseCase findCardPaymentByIdUseCase;
    @BeforeEach
    void setUp() {
        findCardPaymentByIdUseCase = new FindCardPaymentByIdUseCaseImpl(
                cardPaymentService
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowIfIdNull() {
        assertThrows(FieldRequiredException.class, () -> findCardPaymentByIdUseCase.execute(null));
    }
}