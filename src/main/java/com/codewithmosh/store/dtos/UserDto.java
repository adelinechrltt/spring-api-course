package com.codewithmosh.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;
    private LocalDateTime createdAt;
}
