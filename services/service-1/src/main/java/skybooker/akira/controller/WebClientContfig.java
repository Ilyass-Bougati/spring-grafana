package skybooker.akira.controller;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientContfig {
    @Bean
    public WebClient webClient(OpenTelemetry openTelemetry) {
        return WebClient.builder()
                .build();
    }
}
