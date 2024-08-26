package com.example.gatewayapi.config;

import com.example.gatewayapi.filters.GatewayFilters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder, GatewayFilters gatewayFilters) {
        return routeLocatorBuilder.routes()
                .route("book-createor-service",  r -> r.path("/creator-books")
                        .uri("lb://book-createor-service"))
                .route("book-main-page",
                        r -> r.path("/main", "/profile")
                                .filters(f -> f.filter(gatewayFilters.apply(new GatewayFilters.Config())))
                        .uri("lb://book-main-page"))
                .route("authenticate-service",
                        r -> r.path("/api/users/**")
                                .uri("lb://authenticate-service"))
                .build();


    }
}
