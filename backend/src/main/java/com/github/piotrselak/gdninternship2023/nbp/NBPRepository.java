package com.github.piotrselak.gdninternship2023.nbp;

import com.github.piotrselak.gdninternship2023.nbp.domain.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class NBPRepository {
    private final WebClient webClient;

    @Autowired
    public NBPRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public ExchangeRate getExchangeRate(String currencyCode, String date) {
        String uri = String.format("/exchangerates/rates/A/%s/%s", currencyCode, date);
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uri)
                .retrieve();
        return responseSpec.bodyToMono(ExchangeRate.class).block();
    }

    public ExchangeRate getExchangeRateWithQuotations(String currencyCode, int nQuotations) {
        String uri = String.format("/exchangerates/rates/A/%s/last/%d", currencyCode, nQuotations);
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uri)
                .retrieve();
        return responseSpec.bodyToMono(ExchangeRate.class).block();
    }
}
