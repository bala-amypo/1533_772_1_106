package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User register(User user){

        if(user.getRole()==null){
            user.setRole("USER");
        }
        return repo.save(user);
    }

    @Override
    public User findByEmail(String email){
        return repo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));

    }
    
    @Override
    public User findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }
}


