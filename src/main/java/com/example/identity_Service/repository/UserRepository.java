package com.example.identity_Service.repository;

import com.example.identity_Service.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String > {
//    boolean existsUsername(String username);


    boolean existsByUsername(String username);
}
