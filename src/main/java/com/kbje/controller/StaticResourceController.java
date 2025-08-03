package com.kbje.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticResourceController {

    @GetMapping("/favicon.ico")
    public ResponseEntity<Void> getFavicon() {
        // Return empty response to prevent 404 errors
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/robots.txt")
    public ResponseEntity<String> getRobotsTxt() {
        // Return basic robots.txt to prevent 404 errors
        return ResponseEntity.ok("User-agent: *\nDisallow: /");
    }
} 