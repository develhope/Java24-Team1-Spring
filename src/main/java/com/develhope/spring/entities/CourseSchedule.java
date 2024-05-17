package com.develhope.spring.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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
    private Date startDateTime;
    @Column(nullable = false)
    private Date finishDateTime;
    @Column(nullable = false)
    private String link;

    public CourseSchedule(Long id, Course course, Date startDateTime, Date finishDateTime, String link) {
        this.id = id;
        this.course = course;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
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


    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Date finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
