package com.kbje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsonCategoryManagerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(JsonCategoryManagerApplication.class, args);
    }
} 