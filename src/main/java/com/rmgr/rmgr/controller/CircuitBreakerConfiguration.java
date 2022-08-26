package com.rmgr.rmgr.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

public class CircuitBreakerConfiguration {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
            CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(60).slidingWindow(5,5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED).permittedNumberOfCallsInHalfOpenState(0).waitDurationInOpenState(Duration.ofMillis(20000)).build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(config)
                .timeLimiterConfig(TimeLimiterConfig.ofDefaults()).build());
    }
}
