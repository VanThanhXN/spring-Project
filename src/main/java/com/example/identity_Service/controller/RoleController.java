package com.example.identity_Service.controller;

import com.example.identity_Service.dto.request.ApiResponse;
import com.example.identity_Service.dto.request.PermissionRequest;
import com.example.identity_Service.dto.request.RoleRequest;
import com.example.identity_Service.dto.respone.PermissionRespone;
import com.example.identity_Service.dto.respone.RoleRespone;
import com.example.identity_Service.service.PermisionService;
import com.example.identity_Service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;
    @PostMapping
    ApiResponse<RoleRespone> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleRespone>builder()
                .result(roleService.create(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<RoleRespone>> getAllPermissions() {
        return ApiResponse.<List<RoleRespone>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .build();

    }
}
