package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codewithmosh.store.entities.User;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // @RequestMapping("/users")
    ///  RequestMapping by default is also GET

    @GetMapping("/users")
    public Iterable<UserDto> getAllUsers(
            // add query param
            // required = false so that the request param is not mandatory when hitting the endpoint
            // set defaultValue = "" (empty string) to avoid null ptr exception by calling .contains during validation
            @RequestParam(required = false, defaultValue = "") String sort
            ) {

        // validation
        // if sort contains "name" or "email", then automatically sort by name
        if (!Set.of("name", "email").contains(sort))
            sort = "name";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null) {
            ///  return a new response entity with status not found
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
