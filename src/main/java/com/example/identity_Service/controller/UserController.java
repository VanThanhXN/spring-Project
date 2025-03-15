package com.example.identity_Service.controller;

import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.entity.User;
import com.example.identity_Service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

   @PostMapping()
    public User createUser(@RequestBody @Valid UserCreationRequest user) {
       return userService.createUser(user);
   }

   @GetMapping
    public List<User> getAllUsers() {
       return userService.getAllUsers();
   }

   @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
   }

   @PutMapping("/{userId}")
   public User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
       return userService.updateUser(userId, request);
   }

   @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
       userService.deleteUser(userId);
       return "đã xóa";
   }
}
