package com.example.identity_Service.service;

import com.example.identity_Service.dto.request.PermissionRequest;
import com.example.identity_Service.dto.respone.PermissionRespone;
import com.example.identity_Service.entity.Permission;
import com.example.identity_Service.mapper.PermissionMapper;
import com.example.identity_Service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // thay the @autowire = contructer
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermisionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionRespone Crete(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
           permission = permissionRepository.save(permission);
           return permissionMapper.toPermissionRespone(permission);
    }

    public List<PermissionRespone> getdAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionRespone).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
