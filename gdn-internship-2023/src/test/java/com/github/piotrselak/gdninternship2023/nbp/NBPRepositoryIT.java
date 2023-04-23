package com.github.piotrselak.gdninternship2023.nbp;


import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NBPRepositoryIT {
    @Autowired
    private NBPRepository repository;

    @Test
    public void givenCodeAndDate_whenGetExchangeRate_thenReturnExchangeRate() {
        ExchangeRate exRate = repository.getAverageExchangeRate("CAD", "2023-04-21");
        var head = exRate.rates().get(0);
        assertEquals(exRate.code(), "CAD");
        assertEquals(exRate.table(), "A");
        assertEquals(exRate.currency(), "dolar kanadyjski");
        assertTrue(exRate.rates().size() > 0);
        assertEquals(head.no(), "078/A/NBP/2023");
        assertEquals(head.effectiveDate(), "2023-04-21");
        assertEquals(head.mid(), 3.1077);
    }

    @Test
    public void givenCodeAndWeekendDate_whenGetExchangeRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getAverageExchangeRate("CAD", "2023-04-22"));
    }
}
