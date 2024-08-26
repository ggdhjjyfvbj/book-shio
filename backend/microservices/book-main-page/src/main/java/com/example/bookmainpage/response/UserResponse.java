package com.example.bookmainpage.response;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UserResponse {

    private String username;

    private String email;

    private String password;
}
