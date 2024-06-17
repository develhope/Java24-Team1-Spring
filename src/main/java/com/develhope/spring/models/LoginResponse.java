package com.develhope.spring.models;

public class LoginResponse {
    private String jwt;
    private int internalCode;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, int internalCode, String message) {
        this.jwt = jwt;
        this.internalCode = internalCode;
        this.message = message;
    }

    public LoginResponse(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }
}
