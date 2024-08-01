package com.example.bookmainpage.controller;

import com.example.bookmainpage.entity.BookEntity;
import com.example.bookmainpage.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/books-page")
    public String books(Model model) {
        return "pages/books";
    }

    @PostMapping("/boook-create")
    @ResponseBody
    public ResponseEntity<String> book(@RequestBody BookEntity book) {
        bookRepository.save(book);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
