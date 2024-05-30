package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import jakarta.persistence.ManyToOne;

public class IscrizioneDTO {
    private Long id;
    private User user;
    private Course course;
    private String dataIscrizione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(String dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }
}
