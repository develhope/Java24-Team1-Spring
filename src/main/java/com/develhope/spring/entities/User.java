package com.develhope.spring.entities;

import com.develhope.spring.enums.RoleEnum;
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
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isDeleted;

    public User(Long id, String name, String surname, String username, String email, String cellNum, String fiscCode, RoleEnum role, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.role = role;
        this.password = password;
        this.isDeleted = false;
    }

    public User(String name, String surname, String username, String email, String cellNum, String fiscCode, RoleEnum role, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.role = role;
        this.password = password;
        this.isDeleted = false;
    }

    public User() {
        this.isDeleted = false;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}


