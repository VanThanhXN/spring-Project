package com.example.identity_Service.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class UserRespone {
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate birthDate;

}
