package com.example.identity_Service.controller;

import com.example.identity_Service.dto.request.ApiResponse;
import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.dto.respone.UserRespone;
import com.example.identity_Service.entity.User;
import com.example.identity_Service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

   @PostMapping()
   ApiResponse<UserRespone> createUser(@RequestBody @Valid UserCreationRequest user) {
       ApiResponse<UserRespone> apiResponse = new ApiResponse<>();
       apiResponse.setResult(userService.createUser(user));

       return apiResponse;
   }

   @GetMapping
    public List<User> getAllUsers() {
       var authentication = SecurityContextHolder.getContext().getAuthentication();
       log.info("username: {}" , authentication.getName());
       authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
       return userService.getAllUsers();
   }

   @GetMapping("/{userId}")
    public UserRespone getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
   }

   @PutMapping("/{userId}")
   public UserRespone updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
       return userService.updateUser(userId, request);
   }

   @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
       userService.deleteUser(userId);
       return "đã xóa";
   }

    @GetMapping("/myInfo")
    public ApiResponse<UserRespone> getMyInfo() {
       return ApiResponse.<UserRespone>builder()
               .result(userService.getMyInfo())
               .build();

    }
}
