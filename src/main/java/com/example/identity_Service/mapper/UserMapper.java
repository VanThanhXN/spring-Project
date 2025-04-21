package com.example.identity_Service.mapper;

import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.dto.respone.UserRespone;
import com.example.identity_Service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest user);

    UserRespone toUserRespone(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
