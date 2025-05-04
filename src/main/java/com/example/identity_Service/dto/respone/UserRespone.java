package com.example.identity_Service.dto.respone;

import com.example.identity_Service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class UserRespone {
     String id;
     String username;
//     String password;
     String firstName;
     String lastName;
     LocalDate birthDate;
     Set<RoleRespone> roles;

}
