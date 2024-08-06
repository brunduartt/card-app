package org.bancodobrasil.infrastructure.cardpayment.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@TestHTTPEndpoint(CardPaymentResource.class)
class CardPaymentResourceIT extends CardPaymentResourceTest {
}