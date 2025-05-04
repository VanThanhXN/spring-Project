package com.example.identity_Service.mapper;

import com.example.identity_Service.dto.request.PermissionRequest;
import com.example.identity_Service.dto.request.UserCreationRequest;
import com.example.identity_Service.dto.request.UserUpdateRequest;
import com.example.identity_Service.dto.respone.PermissionRespone;
import com.example.identity_Service.dto.respone.UserRespone;
import com.example.identity_Service.entity.Permission;
import com.example.identity_Service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest user);

    PermissionRespone toPermissionRespone(Permission permission);


}
