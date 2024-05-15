package com.develhope.spring.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Course course;
    @Column(nullable = false)
    private OffsetDateTime dateTime;
    @Column(nullable = false)
    private String link;

    public CourseSchedule(Long id, Course course, OffsetDateTime dateTime, String link) {
        this.id = id;
        this.course = course;
        this.dateTime = dateTime;
        this.link = link;
    }

    public CourseSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
