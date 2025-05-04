package com.example.identity_Service.dto.request;

import com.example.identity_Service.validator.DobContraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, max = 12,message = "USER_NOT_FOUND")
     String username;

    @Size(min = 6,message = "PASS_NOT_FOUND", max = 20)
     String password;
     String firstName;
     String lastName;
     @DobContraint(min = 10 , message = "INVALIT_DOB")
     LocalDate birthDate;


}
