package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import jakarta.persistence.*;

public class GradeDTO {
    private Long id;
    private Long student_id;
    private Long course_id;
    private String grade;
    private Boolean finishedCourse;

    public GradeDTO(Long id, Long student_id, Long course_id, String grade, Boolean finishedCourse) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.grade = grade;
        this.finishedCourse = finishedCourse;
    }

    public GradeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getFinishedCourse() {
        return finishedCourse;
    }

    public void setFinishedCourse(Boolean finishedCourse) {
        this.finishedCourse = finishedCourse;
    }
}
