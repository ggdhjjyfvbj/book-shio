package com.example.authenticateservice.error;


import java.util.List;

public record ValidationError(List<String> error) {
}
