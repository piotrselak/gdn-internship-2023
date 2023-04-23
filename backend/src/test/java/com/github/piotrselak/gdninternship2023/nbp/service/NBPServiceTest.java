package com.github.piotrselak.gdninternship2023.nbp.service;

import com.github.piotrselak.gdninternship2023.nbp.domain.BidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.BiggestDifference;
import com.github.piotrselak.gdninternship2023.nbp.domain.MinMaxRates;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import com.github.piotrselak.gdninternship2023.nbp.repository.NBPRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NBPServiceTest {
    private final NBPRepository repository = Mockito.mock(NBPRepository.class);
    private final NBPService service = new NBPService(repository);

    @Test public void givenCodeAndDate_whenGetRate_thenReturnRate() {
        Mockito.when(repository.getAverageRate("USD", "2021-01-01"))
                .thenReturn(new Rate("USD", "2021-01-01", 3.5));
        Rate rate = service.getAverageExchangeRate("USD", "2021-01-01");
        assertEquals("USD", rate.no());
        assertEquals("2021-01-01", rate.effectiveDate());
        assertEquals(3.5, rate.mid());
    }

    @Test public void givenInvalidCode_whenGetRate_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getAverageExchangeRate("US", "2021-01-01"));
    }

    @Test public void givenInvalidDate_whenGetRate_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getAverageExchangeRate("USD", "2021-01"));
    }

    @Test public void givenCodeAndQuotationCount_whenGetMinMaxRates_thenReturnMinMaxRates() {
        Mockito.when(repository.getAverageExchangeRateWithQuotations("USD", 2))
                .thenReturn(new ArrayList<>() {{
                    add(new Rate("USD", "2021-01-01", 3.5));
                    add(new Rate("USD", "2021-01-02", 3.6));
                }});
        MinMaxRates minMaxRates = service.getMinMaxRates("USD", 2);
        assertEquals("USD", minMaxRates.min().no());
        assertEquals("2021-01-01", minMaxRates.min().effectiveDate());
        assertEquals(3.5, minMaxRates.min().mid());
        assertEquals("USD", minMaxRates.max().no());
        assertEquals("2021-01-02", minMaxRates.max().effectiveDate());
        assertEquals(3.6, minMaxRates.max().mid());
    }

    @Test public void givenCodeAndQuotationCount_whenServerBroke_thenThrowException() {
        Mockito.when(repository.getAverageExchangeRateWithQuotations("USD", 2))
                .thenThrow(new EmptyRateArrayException("Couldn't get min and max value from empty collection."));
        assertThrows(RuntimeException.class, () -> service.getMinMaxRates("USD", 2));
    }

    @Test public void givenInvalidCode_whenGetMinMaxRates_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getMinMaxRates("US", 2));
    }

    @Test public void givenInvalidQuotationCount_whenGetMinMaxRates_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getMinMaxRates("USD", 0));
    }

    @Test public void givenCodeAndQuotationCount_whenGetMinMaxRates_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getMinMaxRates("USD", -1));
    }

    @Test public void givenCodeAndTooBigQuotationCount_whenGetMinMaxRates_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getMinMaxRates("USD", 367));
    }

    @Test public void givenCodeAndQuotationCount_whenGetBiggestBidAndAskDifference_thenReturnBiggestDifference() {
        Mockito.when(repository.getBidAskRateWithQuotations("USD", 2))
                .thenReturn(new ArrayList<>() {{
                    add(new BidAskRate("USD", "2021-01-01", 3.5, 3.6));
                    add(new BidAskRate("USD", "2021-01-02", 3.6, 3.7));
                }});
        BiggestDifference rate = service.getBiggestBidAndAskDifference("USD", 2);
        assertEquals(3.5, rate.bid());
        assertEquals(3.7, rate.ask());
        assertEquals(Math.abs(rate.bid() - rate.ask()), rate.difference());
    }

    @Test public void givenCodeAndQuotationCount_whenServerBroke2_thenThrowException() {
        Mockito.when(repository.getBidAskRateWithQuotations("USD", 2))
                .thenThrow(new EmptyRateArrayException("Couldn't get min and max value from empty collection."));
        assertThrows(RuntimeException.class, () -> service.getBiggestBidAndAskDifference("USD", 2));
    }

    @Test public void givenInvalidCode_whenGetBiggestBidAndAskDifference_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getBiggestBidAndAskDifference("US", 2));
    }

    @Test public void givenInvalidQuotationCount_whenGetBiggestBidAndAskDifference_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getBiggestBidAndAskDifference("USD", 0));
    }

    @Test public void givenInvalidQuotationCount2_whenGetBiggestBidAndAskDifference_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getBiggestBidAndAskDifference("USD", -1));
    }

    @Test public void givenInvalidQuotationCount3_whenGetBiggestBidAndAskDifference_thenThrowException() {
        assertThrows(ValidationError.class, () -> service.getBiggestBidAndAskDifference("USD", 367));
    }
}