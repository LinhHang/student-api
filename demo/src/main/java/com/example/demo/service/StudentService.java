package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface StudentService extends JpaRepository<Student, Long> {

    Optional<Student> findOneByEmail(String email);
}
