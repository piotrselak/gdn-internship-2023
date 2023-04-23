package com.github.piotrselak.gdninternship2023.nbp.repository;

import com.github.piotrselak.gdninternship2023.nbp.domain.BidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeAverageRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeBidAskRate;
import com.github.piotrselak.gdninternship2023.nbp.domain.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Repository
public class NBPRepository {
    private final WebClient webClient;

    @Autowired
    public NBPRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Rate getAverageRate(String currencyCode, String date) {
        String uri = String.format("/exchangerates/rates/A/%s/%s", currencyCode, date);
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uri)
                .retrieve();
        ExchangeAverageRate exRate = responseSpec.bodyToMono(ExchangeAverageRate.class).block();
        if (exRate == null || exRate.rates() == null || exRate.rates().size() != 1)
            throw new NullExchangeRateException("Server returned exchange rate, but without proper body.");
        return exRate.rates().get(0);
    }

    public ArrayList<Rate> getAverageExchangeRateWithQuotations(String currencyCode, int nQuotations) {
        String uri = String.format("/exchangerates/rates/A/%s/last/%d", currencyCode, nQuotations);
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uri)
                .retrieve();
        ExchangeAverageRate exRate = responseSpec.bodyToMono(ExchangeAverageRate.class).block();
        if (exRate == null || exRate.rates() == null || exRate.rates().size() < 1)
            throw new NullExchangeRateException("Server returned exchange rate, but without proper body.");
        return exRate.rates();
    }

    public ArrayList<BidAskRate> getBidAskRateWithQuotations(String currencyCode, int nQuotations) {
        String uri = String.format("/exchangerates/rates/C/%s/last/%d", currencyCode, nQuotations);
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uri)
                .retrieve();
        ExchangeBidAskRate exRate = responseSpec.bodyToMono(ExchangeBidAskRate.class).block();
        if (exRate == null || exRate.rates() == null || exRate.rates().size() < 1)
            throw new NullExchangeRateException("Server returned exchange rate, but without proper body.");
        return exRate.rates();
    }
}
