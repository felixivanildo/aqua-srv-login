package com.aquaone.srv.login.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<?> healtEndpoint (){
        return ResponseEntity.ok().build();
    }
    
}
