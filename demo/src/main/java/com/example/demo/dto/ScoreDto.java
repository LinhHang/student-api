package com.example.demo.dto;

import com.example.demo.entity.Score;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ScoreDto implements Serializable {

    private Long id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Integer score;

    public ScoreDto(Long id, @NotNull Long studentId, @NotNull Long subjectId, @NotNull Integer score) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score = score;
    }

    public ScoreDto(Score score) {
        this.id = score.getId();
        this.studentId = score.getStudent().getId();
        this.subjectId = score.getSubject().getId();
        this.score = score.getScore();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
