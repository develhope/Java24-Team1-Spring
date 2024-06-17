package com.develhope.spring.entities;

import com.develhope.spring.enums.CourseType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String startDate;
    @Column(nullable = false)
    private String finishDate;
    @Column(nullable = false)
    private Integer courseLength;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String subject;
    private String description;
    @ManyToOne
    private User tutor;
    @Column
    private Boolean activeCourse;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    public Course(Long id, String name, String startDate, String finishDate, Integer courseLength, Double price, String subject, String description, User tutor, CourseType courseType) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courseLength = courseLength;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.tutor = tutor;
        this.activeCourse = true;
        this.courseType = courseType;
    }

    public Course() {
        this.activeCourse = true;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(Integer courseLength) {
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

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
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
