package com.example.demo.dto;

import com.example.demo.entity.Subject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SubjectDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String code; // mã học phần

    @NotNull
    private Integer creditPoint; // số lượng tín chỉ của môn học

    public SubjectDto(Long id, @NotBlank @Size(max = 50) String name, @NotNull @Size(max = 50) String code, @NotNull Integer creditPoint) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.creditPoint = creditPoint;
    }

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.code = subject.getCode();
        this.creditPoint = subject.getCreditPoint();
    }

    public Subject toEntity() {
        Subject subject = new Subject();
        if (this.id != null) {
            subject.setId(this.id);
        }
        subject.setName(this.name);
        subject.setCode(this.code);
        subject.setCreditPoint(this.creditPoint);
        return subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(Integer creditPoint) {
        this.creditPoint = creditPoint;
    }
}
