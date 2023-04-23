package com.github.piotrselak.gdninternship2023.nbp.repository;

import com.github.piotrselak.gdninternship2023.nbp.domain.BidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NBPRepositoryTest {
    @Autowired
    private NBPRepository repository;

    @Test public void givenCodeAndDate_whenGetRate_thenReturnRate() {
        Rate exRate = repository.getAverageRate("CAD", "2023-04-21");
        assertEquals(exRate.no(), "078/A/NBP/2023");
        assertEquals(exRate.effectiveDate(), "2023-04-21");
        assertEquals(exRate.mid(), 3.1077);
    }

    @Test public void givenCodeAndWeekendDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageRate("CAD", "2023-04-22"));
    }

    @Test public void givenInvalidCodeAndDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageRate("XXX", "2023-04-21"));
    }

    @Test public void givenCodeAndInvalidDate_whenGetRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageRate("CAD", "2023-04-32"));
    }

    @Test public void givenCodeAndQuotationCount_whenGetExchangeRateWithQuotations_thenReturnExchangeRate() {
        ArrayList<Rate> rates = repository.getAverageExchangeRateWithQuotations("CAD", 3);
        assertEquals(rates.size(), 3);
        assertTrue(rates.get(0).no().matches("\\d{3}/A/NBP/\\d{4}"));
    }

    @Test public void givenInvalidCodeAndQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageExchangeRateWithQuotations("XXX", 3));
    }

    @Test public void givenCodeAndInvalidQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageExchangeRateWithQuotations("CAD", 0));
    }

    @Test public void givenCodeAndNegativeQuotationCount_whenGetExchangeRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageExchangeRateWithQuotations("CAD", -5));
    }

    @Test public void givenCodeAndQuotationCount_whenGetBidAskRateWithQuotations_thenReturnBidAskRate() {
        ArrayList<BidAskRate> rates = repository.getBidAskRateWithQuotations("CAD", 3);
        assertEquals(rates.size(), 3);
        assertTrue(rates.get(0).no().matches("\\d{3}/C/NBP/\\d{4}"));
    }

    @Test public void givenInvalidCodeAndQuotationCount_whenGetBidAskRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getBidAskRateWithQuotations("XXX", 3));
    }

    @Test public void givenCodeAndInvalidQuotationCount_whenGetBidAskRateWithQuotations_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getBidAskRateWithQuotations("CAD", 0));
    }
}
