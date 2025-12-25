package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Dummy login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        // Normally, user validation will happen from DB
        if ("admin".equals(request.getUsername()) && "admin".equals(request.getPassword())) {
            String token = jwtTokenProvider.generateToken(request.getUsername());
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
