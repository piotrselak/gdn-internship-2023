package com.github.piotrselak.gdninternship2023.nbp.controller;

import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.service.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// The test class will test integration of all components of the application.
// It's heavier than the service tests. I think that the service tests are enough to test the logic,
// as the controller is just a wrapper for the service, which takes url arguments.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NBPControllerTest {
    @Autowired
    private NBPController controller;

    @Value(value="${local.server.port}")
    private int port;

    @Test void givenCodeAndDate_whenGetRateByCodeAndDate_thenReturnRate() {
        Rate rate = controller.getRateByCodeAndDate("USD", "2023-04-03");
        assertEquals(rate.effectiveDate(), "2023-04-03");
        assertEquals(rate.mid(), 4.3168);
        assertEquals(rate.no(), "065/A/NBP/2023");
    }

    @Test void givenInvalidCode_whenGetRateByCodeAndDate_thenThrowException() {
        assertThrows(ValidationError.class, () -> controller.getRateByCodeAndDate("US", "2023-04-03"));
    }

    @Test void givenInvalidDate_whenGetRateByCodeAndDate_thenThrowException() {
        assertThrows(ValidationError.class, () -> controller.getRateByCodeAndDate("USD", "2023-04-0"));
    }

//    @Test void givenNonExistingCode_whenGetRateByCodeAndDate_thenThrowException() {
//        assertThrows(RateNotFound.class, () -> controller.getRateByCodeAndDate("USX", "2023-04-03"));
//    }

    // There is no point in testing for actual values - they change.
    @Test void givenCodeAndN_whenGetRateMinMaxByCodeAndN_thenReturnMinMaxRates() {
        MinMaxRates minMaxRates = controller.getRateMinMaxByCodeAndN("USD", 3);
        assertTrue(minMaxRates.min().mid() < minMaxRates.max().mid());
    }

    @Test void givenInvalidCode_whenGetRateMinMaxByCodeAndN_thenThrowException() {
        assertThrows(ValidationError.class, () -> controller.getRateMinMaxByCodeAndN("US", 3));
    }

    @Test void givenInvalidN_whenGetRateMinMaxByCodeAndN_thenThrowException() {
        assertThrows(ValidationError.class, () -> controller.getRateMinMaxByCodeAndN("USD", 0));
    }
}