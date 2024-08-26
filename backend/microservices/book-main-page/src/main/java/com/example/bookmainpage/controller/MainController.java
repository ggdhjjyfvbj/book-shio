package com.example.bookmainpage.controller;

import com.example.bookmainpage.entity.BookEntity;
import com.example.bookmainpage.repository.BookRepository;
import com.example.bookmainpage.request.UserRequest;
import com.example.bookmainpage.response.UserResponse;
import com.example.bookmainpage.service.JWTService;

import com.example.bookmainpage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.netty.http.server.HttpServerRequest;


@RestController
public class MainController {

    @Autowired
    BookRepository bookRepository;


    @Autowired
    JWTService jwtService;

    @Autowired
    UserService userService;

    @GetMapping("/main")
    public ResponseEntity<?> books(HttpServletRequest servletRequest) {
        String token = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String username = jwtService.exactUsername(token.substring(7));
        return new ResponseEntity<>(UserRequest.builder().username(username).build(), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(HttpServletRequest servletRequest) {
        String token = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String username = jwtService.exactUsername(token.substring(7));
        UserRequest userRequest = UserRequest.builder().username(username).build();
        return new ResponseEntity<>(userService.userRequest(userRequest), HttpStatus.OK);
    }

    @PostMapping("/boook-create")
    public ResponseEntity<?> book(@RequestBody BookEntity book) {
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }
}
