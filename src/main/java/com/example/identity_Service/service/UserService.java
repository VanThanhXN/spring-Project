package com.example.identity_Service.service;

import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.dto.respone.UserRespone;
import com.example.identity_Service.entity.User;
import com.example.identity_Service.exception.AppException;
import com.example.identity_Service.exception.ErrorCode;
import com.example.identity_Service.mapper.UserMapper;
import com.example.identity_Service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;



import java.util.List;

@Service
@RequiredArgsConstructor // thay the @autowire = contructer
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;



    public UserRespone createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }


        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return  userMapper.toUserRespone(userRepository.save(user));

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserRespone getUser(String id) {
        return userMapper.toUserRespone(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    public UserRespone  updateUser( String userId,UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User k tôn tai"));
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setBirthDate(request.getBirthDate());

        userMapper.updateUser(user,request);

       return userMapper.toUserRespone(userRepository.save(user));


    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
