package com.github.piotrselak.gdninternship2023.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NBPService {
        //http://api.nbp.pl/api/
    private final WebClient webClient = WebClient.create("http://api.nbp.pl/api/");

    public void getAverageExchangeRate(String currencyCode, String date) {

    }
}
