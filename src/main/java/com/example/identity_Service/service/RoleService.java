package com.example.identity_Service.service;

import com.example.identity_Service.dto.request.RoleRequest;
import com.example.identity_Service.dto.respone.RoleRespone;
import com.example.identity_Service.mapper.RoleMapper;
import com.example.identity_Service.repository.PermissionRepository;
import com.example.identity_Service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // thay the @autowire = contructer
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleRespone create(RoleRequest request) {

        var role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));

        role = roleRepository.save(role);
        return roleMapper.toRoleRespone(role);
    }

    public List<RoleRespone> getAll() {
        return roleRepository.findAll()
                .stream().map(roleMapper::toRoleRespone)
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
