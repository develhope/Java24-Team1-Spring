package com.develhope.spring.models;

import com.develhope.spring.enums.RoleEnum;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDTO {
    private String username;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
    private String email;
    private String cellNum;
    private String fiscCode;
    private RoleEnum role;

    public RegistrationDTO(String username, String password, String repeatPassword, String name, String surname, String email, String cellNum, String fiscCode, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.role = role;
    }

    public RegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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
}
