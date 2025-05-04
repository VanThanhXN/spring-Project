package com.example.identity_Service.mapper;

import com.example.identity_Service.dto.request.PermissionRequest;
import com.example.identity_Service.dto.request.RoleRequest;
import com.example.identity_Service.dto.respone.RoleRespone;
import com.example.identity_Service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions" , ignore = true)
    Role toRole(RoleRequest request);
    RoleRespone toRoleRespone(Role role);
}

