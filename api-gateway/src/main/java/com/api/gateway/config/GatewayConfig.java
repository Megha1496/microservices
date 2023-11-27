package com.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class GatewayConfig {

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/user/**")
                        .uri("lb://USER-SERVICE")
                )
                .route("hotel-service", r -> r
                        .path("/hotel/**")
                        .uri("lb://HOTEL-SERVICE")
                )
                .route("rating-service", r -> r
                        .path("/rating/**")
                        .uri("lb://RATING-SERVICE")
                )
                .build();
    }
}

