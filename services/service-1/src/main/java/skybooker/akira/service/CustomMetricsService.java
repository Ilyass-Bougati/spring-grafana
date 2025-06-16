package skybooker.akira.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetricsService {
    private final Counter counter;

    public CustomMetricsService(MeterRegistry meterRegistry) {
        counter = Counter.builder("requests_counter_1")
                .description("The number of requests")
                .tags("environment", "development")
                .register(meterRegistry);
    }

    public void incrementCustomMetric() {
        counter.increment();
    }
}
