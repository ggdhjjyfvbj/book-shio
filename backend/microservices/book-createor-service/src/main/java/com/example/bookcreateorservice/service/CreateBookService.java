package com.example.bookcreateorservice.service;

import com.example.bookcreateorservice.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreateBookService {


    @Autowired
    public WebClient.Builder webClient;

    public String crateBook(BookDTO bookDTO) {
        return webClient
                .build()
                .post()
                .uri("lb://book-main-page/boook-create")
                .body(BodyInserters.fromValue(bookDTO))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
