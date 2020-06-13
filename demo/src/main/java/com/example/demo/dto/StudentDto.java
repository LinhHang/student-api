package com.example.demo.dto;

import com.example.demo.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class StudentDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    private Integer age;

    private Boolean gender;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String email;

    private String address;

    public StudentDto(Long id, @NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName, Integer age, Boolean gender, @NotNull @Email @Size(min = 5, max = 254) String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    public StudentDto(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.age = student.getAge();
        this.gender = student.getGender();
        this.email = student.getEmail();
        this.address = student.getAddress();
    }

    public Student toEntity() {
        Student student = new Student();
        if (this.getId() != null) {
            student.setId(this.getId());
        }
        if (this.getFirstName() != null) {
            student.setFirstName(this.getFirstName());
        }
        if (this.getLastName() != null) {
            student.setLastName(this.getLastName());
        }
        if (this.getAddress() != null) {
            student.setAddress(this.getAddress());
        }
        if (this.getAge() != null) {
            student.setAge(this.getAge());
        }
        if (this.getGender() != null) {
            student.setGender(this.getGender());
        }
        student.setEmail(this.getEmail());
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
