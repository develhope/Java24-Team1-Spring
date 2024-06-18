package com.develhope.spring.models.DTO.requestDTO;

import com.develhope.spring.enums.RoleEnum;

public class UserRequestDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String cellNum;
    private String fiscCode;
    private RoleEnum role;
    private String password;

    public UserRequestDTO(Long id, String name, String surname, String username, String email, String cellNum, String fiscCode, RoleEnum role, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.role = role;
        this.password = password;
    }

    public UserRequestDTO(String name, String surname, String username, String email, String cellNum, String fiscCode, RoleEnum role, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.role = role;
        this.password = password;
    }

    public UserRequestDTO() {
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
}
