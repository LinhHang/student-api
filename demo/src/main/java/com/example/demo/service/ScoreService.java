package com.example.demo.service;

import com.example.demo.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ScoreService extends JpaRepository<Score, Long> {

    Page<Score> findAllBySubject_Id(Long subjectId, Pageable pageable);

    Page<Score> findAllByStudent_Id(Long studentId, Pageable pageable);

    Page<Score> findAllByStudent_IdAndSubject_Id(Long studentId, Long subjectId, Pageable pageable);

}
