package org.bancodobrasil.infrastructure.cardpayment.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CardPaymentRepository implements PanacheRepositoryBase<CardPayment, UUID> {}
