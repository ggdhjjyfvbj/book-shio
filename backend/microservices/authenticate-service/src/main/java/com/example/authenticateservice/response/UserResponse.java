package com.example.authenticateservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserResponse {
    public UserResponse(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private String username;

    private String email;

    private String password;
}
