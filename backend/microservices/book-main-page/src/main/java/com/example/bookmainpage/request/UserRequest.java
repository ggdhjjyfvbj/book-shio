package com.example.bookmainpage.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    private String username;

}
