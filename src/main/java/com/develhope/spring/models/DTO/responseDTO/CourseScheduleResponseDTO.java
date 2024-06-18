package com.develhope.spring.models.DTO.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;

public class CourseScheduleResponseDTO {

    private Long id;
    private Long course_id;
    private String startDateTime;
    private String finishDateTime;
    private String link;

    public CourseScheduleResponseDTO(Long id, Long course_id, String startDateTime, String finishDateTime, String link) {
        this.id = id;
        this.course_id = course_id;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.link = link;
    }

    public CourseScheduleResponseDTO() {
    }

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

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(String finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
