package com.codewithmosh.store.controllers;

import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmosh.store.entities.User;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // @RequestMapping("/users")
    ///  RequestMapping by default is also GET

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
