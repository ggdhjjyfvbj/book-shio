package com.example.bookcreateorservice.controller;

import com.example.bookcreateorservice.dto.BookDTO;
import com.example.bookcreateorservice.service.CreateBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    CreateBookService createBookService;

    @PostMapping("/creator-books")
    public ResponseEntity<?> createBook2(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(createBookService.crateBook(bookDTO), HttpStatus.OK);
    }
}
