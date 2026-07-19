package com.solix.supportai.qdrant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class QdrantConfig {

    @Bean
    public RestClient qdrantRestClient() {

        return RestClient.builder()
                .baseUrl("http://localhost:6333")
                .build();

    }

}