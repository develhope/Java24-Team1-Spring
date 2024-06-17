package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.User;
import com.develhope.spring.enums.CourseType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;


import java.util.Date;

public class CourseDTO {

    private Long id;
    private String name;
    private String startDate;
    private String finishDate;
    private Integer courseLength;
    private Double price;
    private String subject;
    private String description;
    private Long tutor_id;
    private CourseType courseType;

    public CourseDTO(Long id, String name, String startDate, String finishDate, Integer courseLength, Double price, String subject, String description, Long tutor_id, CourseType courseType) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courseLength = courseLength;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.tutor_id = tutor_id;
        this.courseType = courseType;
    }

    public CourseDTO() {
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

    public Long getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(Long tutor_id) {
        this.tutor_id = tutor_id;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
