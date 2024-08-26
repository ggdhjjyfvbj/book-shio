package com.example.authenticateservice.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class TokenRequest {

    private String username;
}
