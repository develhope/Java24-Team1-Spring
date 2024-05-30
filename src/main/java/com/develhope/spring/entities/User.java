package com.develhope.spring.entities;

import jakarta.persistence.*;

@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    private String cellNum;
    @Column(nullable = false)
    private String fiscCode;
    @Column(nullable = false)
    private Boolean isATutor;
    @Column(nullable = false)
    private String password;

    public User(Long id, String name, String surname, String username, String email, String cellNum, String fiscCode, Boolean isATutor, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.isATutor = isATutor;
        this.password = password;
    }

    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    public String getFiscCode() {
        return fiscCode;
    }

    public void setFiscCode(String fiscCode) {
        this.fiscCode = fiscCode;
    }

    public Boolean getATutor() {
        return isATutor;
    }

    public void setATutor(Boolean ATutor) {
        isATutor = ATutor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


