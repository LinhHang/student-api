package com.example.demo.service;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SubjectService extends JpaRepository<Subject, Long> {

    Optional<Subject> findOneByCode(String code);
}
