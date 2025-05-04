package com.example.identity_Service.controller;

import com.example.identity_Service.dto.request.ApiResponse;
import com.example.identity_Service.dto.request.PermissionRequest;
import com.example.identity_Service.dto.respone.PermissionRespone;
import com.example.identity_Service.entity.Permission;
import com.example.identity_Service.service.PermisionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermisionService permisionService;
    @PostMapping
    ApiResponse<PermissionRespone> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionRespone>builder()
                .result(permisionService.Crete(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionRespone>> getAllPermissions() {
        return ApiResponse.<List<PermissionRespone>>builder()
                .result(permisionService.getdAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permisionService.delete(permission);
        return ApiResponse.<Void>builder()
                .build();

    }
}
