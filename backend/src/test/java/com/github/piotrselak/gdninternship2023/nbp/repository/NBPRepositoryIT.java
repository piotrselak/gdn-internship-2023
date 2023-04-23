package com.github.piotrselak.gdninternship2023.nbp.repository;

import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.*;

// TODO: it should run on another maven cycle
@SpringBootTest
public class NBPRepositoryIT {
    @Autowired
    private NBPRepository repository;

    @Test public void givenCodeAndDate_whenGetRate_thenReturnRate() {
        Rate exRate = repository.getRate("CAD", "2023-04-21");
        assertEquals(exRate.no(), "078/A/NBP/2023");
        assertEquals(exRate.effectiveDate(), "2023-04-21");
        assertEquals(exRate.mid(), 3.1077);
    }

    @Test public void givenCodeAndWeekendDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getRate("CAD", "2023-04-22"));
    }

    @Test public void givenInvalidCodeAndDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getRate("XXX", "2023-04-21"));
    }

    @Test public void givenCodeAndInvalidDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getRate("CAD", "2023-04-32"));
    }

    @Test public void givenCodeAndQuotationCount_whenGetExchangeRateWithQuotations_thenReturnExchangeRate() {
        ExchangeRate exRate = repository.getExchangeRateWithQuotations("CAD", 3);
        assertEquals(exRate.code(), "CAD");
        assertEquals(exRate.currency(), "dolar kanadyjski");
        assertEquals(exRate.rates().size(), 3);
    }

    @Test public void givenInvalidCodeAndQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getExchangeRateWithQuotations("XXX", 3));
    }

    @Test public void givenCodeAndInvalidQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getExchangeRateWithQuotations("CAD", 0));
    }

    @Test public void givenCodeAndNegativeQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getExchangeRateWithQuotations("CAD", -5));
    }
}
