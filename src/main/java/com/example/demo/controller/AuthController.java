package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userSer;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
    
        return userSer.register(user);
    }
    
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userSer.findByEmail(email);
    }
    
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userSer.findById(id);
    }

    
}
