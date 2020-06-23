package com.example.demo.controller;

import com.example.demo.dto.ScoreDto;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.service.ScoreService;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ScoreController {

    private final ScoreService scoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    ScoreController(ScoreService scoreService, StudentService studentService, SubjectService subjectService) {
        this.scoreService = scoreService;
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @GetMapping("/api/scores")
    public ResponseEntity<Page<Score>> filterScore(@RequestParam(required = false) Long studentId,
                                                   @RequestParam(required = false) Long subjectId,
                                                   Pageable pageable) {
        if (studentId != null && subjectId != null) {
            return new ResponseEntity<>(scoreService.findAllByStudent_IdAndSubject_Id(studentId, subjectId, pageable), HttpStatus.OK);
        } else if (studentId != null) {
            return new ResponseEntity<>(scoreService.findAllByStudent_Id(studentId, pageable), HttpStatus.OK);
        } else if (subjectId != null) {
            return new ResponseEntity<>(scoreService.findAllBySubject_Id(subjectId, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(scoreService.findAll(pageable), HttpStatus.OK);
        }
    }

    @PostMapping("/api/scores")
    public ResponseEntity<ScoreDto> saveScore(@Valid @RequestBody ScoreDto scoreDto) {
        Optional<Subject> subjectScore = subjectService.findById(scoreDto.getSubjectId());
        Optional<Student> studentScore = studentService.findById(scoreDto.getStudentId());
        if (scoreDto.getId() != null || !subjectScore.isPresent() || !studentScore.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Score score = new Score();
            score.setStudent(studentScore.get());
            score.setSubject(subjectScore.get());
            score.setScore(scoreDto.getScore());
            scoreService.save(score);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


}
