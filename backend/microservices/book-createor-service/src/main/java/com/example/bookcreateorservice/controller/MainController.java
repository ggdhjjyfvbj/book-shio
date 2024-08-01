package com.example.bookcreateorservice.controller;

import com.example.bookcreateorservice.dto.BookDTO;
import com.example.bookcreateorservice.service.CreateBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class MainController {

    @Autowired
    CreateBookService createBookService;

    @GetMapping("/creator-books")
    public String createBook(Model model) {
        return "createForm";
    }

    @PostMapping("/creator-books")
    public String createBook2(Model model, @ModelAttribute BookDTO bookDTO) {
        createBookService.crateBook(bookDTO);
        return "redirect:/creator-books";
    }
}
