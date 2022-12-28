package com.example.bookstore_application_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BookStoreApplicationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplicationBackendApplication.class, args);
        System.out.println("Welcome to BookStore Application 2.0");
    }

}
