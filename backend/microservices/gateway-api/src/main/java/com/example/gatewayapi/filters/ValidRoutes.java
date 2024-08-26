package com.example.gatewayapi.filters;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ValidRoutes {

    private final List<String> validRoutes = List.of("/api/users/register", "/api/users/login");

    private final Predicate<ServerHttpRequest> isSecured = request -> validRoutes
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> getIsSecured() {
        return isSecured;
    }
}
