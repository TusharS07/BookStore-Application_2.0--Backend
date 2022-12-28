package com.example.bookstore_application_backend.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookStoreConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }


}