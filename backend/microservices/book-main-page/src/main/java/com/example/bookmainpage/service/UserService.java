package com.example.bookmainpage.service;

import com.example.bookmainpage.request.UserRequest;
import com.example.bookmainpage.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    WebClient.Builder webClient;

    public UserResponse userRequest(UserRequest username) {
        return webClient
                .build()
                .post()
                .uri("lb://authenticate-service/api/users/token")
                .body(BodyInserters.fromValue(username))
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }

}
