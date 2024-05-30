package com.develhope.spring.models.DTO;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String cellNum;
    private String fiscCode;
    private Boolean isATutor;
    private String password;

    public UserDTO(String name, String surname, String username, String email, String cellNum, String fiscCode, Boolean isATutor, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.cellNum = cellNum;
        this.fiscCode = fiscCode;
        this.isATutor = isATutor;
        this.password = password;
    }

    public UserDTO() {
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
