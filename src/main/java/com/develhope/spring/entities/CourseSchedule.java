package com.develhope.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Course course;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateTime;
    @Column(nullable = false)
    private String link;

    public CourseSchedule(Long id, Course course, Date dateTime, String link) {
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
