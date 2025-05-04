package com.example.identity_Service.service;

import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.dto.respone.UserRespone;
import com.example.identity_Service.entity.User;
import com.example.identity_Service.enums.Role;
import com.example.identity_Service.exception.AppException;
import com.example.identity_Service.exception.ErrorCode;
import com.example.identity_Service.mapper.UserMapper;
import com.example.identity_Service.repository.RoleRepository;
import com.example.identity_Service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor // thay the @autowire = contructer
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;



    public UserRespone createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        phan quyen
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        return  userMapper.toUserRespone(userRepository.save(user));

    }

    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserRespone getUser(String id) {
        log.info("Get  users  by id");
        return userMapper.toUserRespone(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    public UserRespone  updateUser( String userId,UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User k tôn tai"));
        userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

       return userMapper.toUserRespone(userRepository.save(user));


    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserRespone getMyInfo(){
        var context =SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user =userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserRespone(user);

    }


}
