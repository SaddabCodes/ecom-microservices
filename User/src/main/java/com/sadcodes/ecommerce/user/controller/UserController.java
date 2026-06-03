package com.sadcodes.ecommerce.user.controller;


import com.sadcodes.ecommerce.user.dto.UserRequest;
import com.sadcodes.ecommerce.user.dto.UserResponse;
import com.sadcodes.ecommerce.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> fetchAllUser() {
        return ResponseEntity.ok(userService.fetchAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponse>> fetchUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.fetchUser(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {
        return new ResponseEntity<>(userService.updateUser(id, updatedUserRequest), HttpStatus.OK);
    }
}
