    package com.example.identity_Service.dto.respone;

    import com.example.identity_Service.entity.Permission;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.util.Set;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults( level = AccessLevel.PRIVATE)
    public class RoleRespone {
        String name;
        String description;
        Set<PermissionRespone> permissions;

    }
