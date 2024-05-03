package com.example.biblio;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class BiblioApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiblioApplication.class, args);
    }
}
