package com.codewithmosh.store.controllers;

import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null) {
            ///  return a new response entity with status not found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
