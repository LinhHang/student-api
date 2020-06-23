package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

}
