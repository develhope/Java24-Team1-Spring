package com.develhope.spring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "utenti_corsi")
public class Iscrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    private String dataIscrizione;
    private Boolean payed;
    @Column(nullable = false)
    private Boolean isDeleted;

    public Iscrizione(Long id, User user, Course course, String dataIscrizione, Boolean payed) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.dataIscrizione = dataIscrizione;
        this.payed= payed;
        this.isDeleted = false;
    }

    public Iscrizione() {
        this.isDeleted = false;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
