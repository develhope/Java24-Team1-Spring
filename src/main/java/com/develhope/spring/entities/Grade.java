package com.develhope.spring.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User student ;
    @ManyToOne
    private Course course;
    @Column(nullable = false)
    private String grade;
    @Column
    private Boolean finishedCourse;
    @Column(nullable = false)
    private Boolean isDeleted;

    public Grade(Long id, User student, Course course, String grade, Boolean finishedCourse) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.finishedCourse = finishedCourse;
        this.isDeleted = false;
    }

    public Grade() {
        this.isDeleted = false;
    }

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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
