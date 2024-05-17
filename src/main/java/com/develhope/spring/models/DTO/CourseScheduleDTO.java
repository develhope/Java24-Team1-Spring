package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseScheduleDTO {

    private Long id;
    private Long course_id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDateTime;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date finishDateTime;
    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return course_id;
    }

    public void setCourse(Long course_id) {
        this.course_id = course_id;
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
