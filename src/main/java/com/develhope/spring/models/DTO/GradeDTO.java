package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import jakarta.persistence.*;

public class GradeDTO {
    private Long id;
    private User student;
    private Course course;
    private String grade;
    private Boolean finishedCourse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
