package com.example.authenticateservice.dto;


import lombok.Data;

@Data
public class UserEntityDTO {

    private String username;

    private String email;

    private String password;

    private String confirmPassword;
}
