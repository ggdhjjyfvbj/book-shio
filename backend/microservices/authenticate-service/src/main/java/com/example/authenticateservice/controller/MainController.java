package com.example.authenticateservice.controller;

import com.example.authenticateservice.dto.AuthEntityDTO;
import com.example.authenticateservice.dto.UserEntityDTO;
import com.example.authenticateservice.entity.UsersEntity;
import com.example.authenticateservice.error.ValidationError;
import com.example.authenticateservice.repository.UserRepository;
import com.example.authenticateservice.request.TokenRequest;
import com.example.authenticateservice.response.SuccesResponse;
import com.example.authenticateservice.response.TokenResponse;
import com.example.authenticateservice.response.UserResponse;
import com.example.authenticateservice.service.interfaces.UsersWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class MainController {

    @Autowired
    UsersWrapper usersWrapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> register(Model model, @Valid @RequestBody UserEntityDTO userEntityDTO, BindingResult bindingResult) {
        if (userRepository.findByUsername(userEntityDTO.getUsername()).isPresent()) {
            bindingResult.addError(new FieldError("userEntityDTO", "username", "Данный логин уже занят"));
        }

        if (userRepository.findByEmail(userEntityDTO.getEmail()) != null) {
            bindingResult.addError(new FieldError("userEntityDTO", "email", "Данный email уже кем то используется"));
        }
        if (!userEntityDTO.getPassword().equals(userEntityDTO.getConfirmPassword())) {
            bindingResult.addError(new FieldError("userEntityDTO", "confirmPassword", "Пароли не совпадают"));
        }


        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {

            errors.add(fieldError.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ValidationError(errors), HttpStatus.OK);
        }

        usersWrapper.saveEntity(UsersEntity.builder()
                .username(userEntityDTO.getUsername())
                .email(userEntityDTO.getEmail())
                .password(userEntityDTO.getPassword()).build());
        return new ResponseEntity<>(new SuccesResponse("SUCCESS"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthEntityDTO authEntityDTO, HttpServletRequest servletRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authEntityDTO.getUsername(), authEntityDTO.getPassword());

        authenticationToken.setDetails(new WebAuthenticationDetails(servletRequest.getRemoteAddr(), servletRequest.getRequestedSessionId()));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String generatedToken = usersWrapper.generateToken(authEntityDTO.getUsername());

            return new ResponseEntity<>(new TokenResponse(generatedToken), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid login or password", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody TokenRequest tokenRequest) {
        UsersEntity users = userRepository.findByUsername(tokenRequest.getUsername()).get();
        UserResponse userResponse = new UserResponse(users.getUsername(), users.getEmail(), users.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
