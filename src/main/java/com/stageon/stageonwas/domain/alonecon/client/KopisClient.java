package com.stageon.stageonwas.domain.alonecon.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class KopisClient {
    private final WebClient webClient;
    private final String serviceKey;

    public KopisClient(WebClient kopisWebClient, @Value("${kopis.service-key}") String serviceKey) {
        this.webClient = kopisWebClient;
        this.serviceKey = serviceKey;
    }

    public Mono<String> fetchDetailXml(String mt20id) {
        return webClient.get()
                .uri(b -> b.path("/pblprfr/{id}")
                        .queryParam("service", serviceKey)
                        .build(mt20id))
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(8));
    }
}
