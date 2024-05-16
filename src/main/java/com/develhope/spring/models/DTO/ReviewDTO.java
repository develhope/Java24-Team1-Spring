package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import jakarta.persistence.*;

public class ReviewDTO {
    private Long id;
    private User student;
    private Course course;
    private String review;

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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
