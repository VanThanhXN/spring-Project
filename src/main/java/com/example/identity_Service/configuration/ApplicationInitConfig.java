package com.example.identity_Service.configuration;

import com.example.identity_Service.entity.User;
import com.example.identity_Service.enums.Role;
import com.example.identity_Service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor // thay the @autowire = contructer
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class ApplicationInitConfig  {

    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                 var roles = new HashSet<String>();
                 roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
//                        .roles(roles)
                        .password(passwordEncoder.encode("admin"))
                        .build();

                userRepository.save(user);
                log.warn("admin user created with admin password");


            }
        };
    };

}
