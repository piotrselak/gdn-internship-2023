package com.github.piotrselak.gdninternship2023.nbp;


import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NBPRepositoryIT {
    @Autowired
    private NBPRepository repository;

    @Test
    public void givenCodeAndDate_whenGetExchangeRate_thenReturnExchangeRate() {
        ExchangeRate exRate = repository.getAverageExchangeRate("CAD", "2023-04-21");
        assertEquals(exRate.code(), "CAD");
        assertEquals(exRate.table(), "A");
        assertEquals(exRate.currency(), "dolar kanadyjski");
        assertTrue(exRate.rates().size() > 0);
    }

}
