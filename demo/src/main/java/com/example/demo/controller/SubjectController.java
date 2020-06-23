package com.example.demo.controller;

import com.example.demo.dto.SubjectDto;
import com.example.demo.entity.Subject;
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
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/api/subjects")
    public ResponseEntity<Page<SubjectDto>> getAllSubject(Pageable pageable) {
        return new ResponseEntity<>(subjectService.findAll(pageable).map(SubjectDto::new), HttpStatus.OK);
    }

    @GetMapping("/api/subjects/{subjectId}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long subjectId) {
        Optional<Subject> subject = subjectService.findById(subjectId);
        return subject.map(value -> new ResponseEntity<>(new SubjectDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/subjects")
    public ResponseEntity<SubjectDto> saveSubjectInfo(@Valid @RequestBody SubjectDto subjectDto) {
        if (subjectDto.getId() != null || subjectService.findOneByCode(subjectDto.getCode()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            subjectService.save(subjectDto.toEntity());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/api/subjects")
    public ResponseEntity<SubjectDto> updateSubjectInfo(@Valid @RequestBody SubjectDto subjectDto) {
        if (subjectDto.getId() == null || !subjectService.findById(subjectDto.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            subjectService.save(subjectDto.toEntity());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
