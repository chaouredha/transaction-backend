package com.reactive.transaction.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final String urlApiEvents;

    public WebClientConfig(@Value("${apis.url.event}") String urlApiEvents) {
        this.urlApiEvents = urlApiEvents;
    }


    @Bean("webClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(urlApiEvents)
                .build();
    }
}
