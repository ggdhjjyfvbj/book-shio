package com.example.authenticateservice.controller;

import com.example.authenticateservice.dto.UserEntityDTO;
import com.example.authenticateservice.entity.UsersEntity;
import com.example.authenticateservice.repository.UserRepository;
import com.example.authenticateservice.service.interfaces.UsersWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class MainController {

    @Autowired
    UsersWrapper usersWrapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model, @Valid @ModelAttribute(name = "userEntityDTO") UserEntityDTO userEntityDTO) {
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute(name = "userEntityDTO") UserEntityDTO userEntityDTO, BindingResult bindingResult) {
        if (userRepository.findByUsername(userEntityDTO.getUsername()).isPresent()) {
            bindingResult.addError(new FieldError("userEntityDTO", "username", "Данный логин уже занят"));
        }

        if (userRepository.findByEmail(userEntityDTO.getEmail()) != null) {
            bindingResult.addError(new FieldError("userEntityDTO", "username", "Данный логин уже кем то используется"));
        }

        if (bindingResult.hasErrors()) {
            return "redirect:register";
        }

        usersWrapper.saveEntity(UsersEntity.builder()
                .username(userEntityDTO.getUsername())
                .email(userEntityDTO.getEmail())
                .password(userEntityDTO.getPassword()).build());
        return "redirect:register";
    }
}
