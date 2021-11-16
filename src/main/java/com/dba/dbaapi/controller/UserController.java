package com.dba.dbaapi.controller;

import com.dba.dbaapi.model.User;
import com.dba.dbaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    public List<User> consultaUsuarios() {
        return repository.findAll();
    }
}
