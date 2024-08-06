package org.bancodobrasil.core.cardpayment.ports;

import org.bancodobrasil.core.cardpayment.entity.CardPaymentDomain;

import java.util.UUID;

public interface CardPaymentService {
    /**
     * Finds a CardPayment by id
     * @param id CardPayment id
     * @return CardPaymentDomain
     * @throws Exception CardPayment not found
     */
    CardPaymentDomain findById(UUID id) throws Exception;
    /**
     * Creates a new CardPayment and returns it
     * @param domain CardPayment to be created
     * @return new CardPaymentDomain with assigned id
     */
    CardPaymentDomain create(CardPaymentDomain domain);
}
