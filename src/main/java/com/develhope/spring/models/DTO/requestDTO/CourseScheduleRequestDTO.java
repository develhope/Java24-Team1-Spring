package com.develhope.spring.models.DTO.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;

public class CourseScheduleRequestDTO {

    private Long id;
    private Long course_id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private DateTime startDateTime;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private DateTime finishDateTime;
    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
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
}
