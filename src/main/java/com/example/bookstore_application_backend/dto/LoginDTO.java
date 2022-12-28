package com.example.bookstore_application_backend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {
    private String email;
    private String password;
}
