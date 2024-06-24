package com.develhope.spring.models.DTO;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import jakarta.persistence.ManyToOne;

public class IscrizioneDTO {
    private Long id;
    private Long user;
    private Long course;
    private String dataIscrizione;
    private Boolean payed;


    public IscrizioneDTO(Long id, Long user, Long course, String dataIscrizione, Boolean payed) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.dataIscrizione = dataIscrizione;
        this.payed = payed;
    }

    public IscrizioneDTO() {
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public String getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(String dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }
}
