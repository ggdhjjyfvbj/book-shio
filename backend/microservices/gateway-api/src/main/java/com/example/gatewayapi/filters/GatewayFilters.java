package com.example.gatewayapi.filters;


import com.example.gatewayapi.service.JWTService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GatewayFilters  extends AbstractGatewayFilterFactory<GatewayFilters.Config> {

    @Autowired
    JWTService jwtService;

    @Autowired
    ValidRoutes routes;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routes.getIsSecured().test(exchange.getRequest())) {
                String token = "";

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if (auth != null && auth.startsWith("Bearer ")) {
                    token = auth.substring(7);
                }


                try {
                    jwtService.validationToken(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return chain.filter(exchange);
        };
    }


    public static class Config {

    }

    
}
