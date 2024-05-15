package com.develhope.spring.entities;

import com.develhope.spring.models.CourseType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date finishDate;
    @Column(nullable = false)
    private String courseLength;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String subject;
    private String description;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private Boolean activeCourse;
    @Column(nullable = false)
    private CourseType courseType;

    public Course(Long id, String name, Date startDate, Date finishDate, String courseLength, Double price, String subject, String description, User user, Boolean activeCourse, CourseType courseType) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courseLength = courseLength;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.user = user;
        this.activeCourse = activeCourse;
        this.courseType = courseType;
    }

    public Course() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(String courseLength) {
        this.courseLength = courseLength;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActiveCourse() {
        return activeCourse;
    }

    public void setActiveCourse(Boolean activeCourse) {
        this.activeCourse = activeCourse;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
