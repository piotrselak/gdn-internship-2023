package com.github.piotrselak.gdninternship2023.nbp.repository;


import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
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
    public void givenCodeAndDate_whenGetExchangeRate_thenReturnRate() {
        Rate exRate = repository.getRate("CAD", "2023-04-21");
        assertEquals(exRate.no(), "078/A/NBP/2023");
        assertEquals(exRate.effectiveDate(), "2023-04-21");
        assertEquals(exRate.mid(), 3.1077);
    }

    @Test
    public void givenCodeAndWeekendDate_whenGetExchangeRate_thenThrowException() {
        assertThrowsExactly(WebClientResponseException.NotFound.class,
                () -> repository.getRate("CAD", "2023-04-22"));
    }
}
