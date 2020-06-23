package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<Page<StudentDto>> getAllStudent(Pageable pageable) {
        System.out.println("Hello Linh");
        return new ResponseEntity<>(studentService.findAll(pageable).map(StudentDto::new), HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/scores")
    public  ResponseEntity<Object> getStudentCores(@PathVariable Long studentId) {
        Object[] result = {
                studentId,
                "title"
        };

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = studentService.findById(studentId);
        return student.map(value -> new ResponseEntity<>(new StudentDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> saveStudentInfo(@RequestBody @Valid StudentDto studentDto) {
        if (studentDto.getId() != null || studentService.findOneByEmail(studentDto.getEmail()).isPresent()) {
            throw new ValidationException();
        } else {
            studentService.save(studentDto.toEntity());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/students")
    public ResponseEntity<StudentDto> updateStudentInfo(@RequestBody @Valid StudentDto studentDto) {
        Optional<Student> optionalStudent = studentService.findById(studentDto.getId());
        if (studentDto.getId() == null || !optionalStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Student student = optionalStudent.get();
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            student.setEmail(studentDto.getEmail());
            if (studentDto.getGender() != null) {
                student.setGender(studentDto.getGender());
            }
            if (studentDto.getAge() != null) {
                student.setAge(studentDto.getAge());
            }
            if (studentDto.getAddress() != null) {
                student.setAddress(studentDto.getAddress());
            }
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
