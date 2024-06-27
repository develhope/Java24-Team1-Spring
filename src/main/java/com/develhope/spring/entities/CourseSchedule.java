package com.develhope.spring.entities;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

@Entity
@Table
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Course course;
    @Column(nullable = false)
    private DateTime startDateTime;
    @Column(nullable = false)
    private DateTime finishDateTime;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private Boolean isDeleted;

    public CourseSchedule(Long id, Course course, DateTime startDateTime, DateTime finishDateTime, String link) {
        this.id = id;
        this.course = course;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.link = link;
        this.isDeleted = false;
    }

    public CourseSchedule() {
        this.isDeleted = false;
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


    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public DateTime getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(DateTime finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
