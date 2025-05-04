package com.example.identity_Service.dto.respone;

// token có phải hệ thống này tạo    ra k

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRespone {
    String name;
    String description;
}