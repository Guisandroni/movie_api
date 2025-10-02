
package dev.guisandroni.movieapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.base-url}")
    private String tmdbApiBaseUrl;

    @Value("${tmdb.api.access-token}")
    private String tmdbApiAccessToken;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(tmdbApiBaseUrl)
                .defaultHeader("Authorization", "Bearer " + tmdbApiAccessToken)
                .build();
    }
}
