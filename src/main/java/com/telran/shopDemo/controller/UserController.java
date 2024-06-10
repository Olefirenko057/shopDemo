package com.telran.shopDemo.controller;

import com.telran.shopDemo.dto.UserDto;
import com.telran.shopDemo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @GetMapping
    private List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    private UserDto getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }

    @PostMapping
    private UserDto insertUser(@RequestBody UserDto userDto) {
        return service.insertUser(userDto);
    }

    @PutMapping
    private UserDto updateUser(@RequestBody UserDto userDto) {
        return service.updateUser(userDto);
    }
}
