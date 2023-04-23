package com.github.piotrselak.gdninternship2023.nbp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NBPConfiguration {
    @Bean
    public WebClient createWebClient() {
        return WebClient.create("http://api.nbp.pl/api");
    }
}
