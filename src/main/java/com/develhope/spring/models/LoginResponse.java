package com.develhope.spring.models;

public class LoginResponse {
    private String jwt;
    private int internalCode;
    private String message;

    public LoginResponse(String jwt, int internalCode, String message) {
        this.jwt = jwt;
        this.internalCode = internalCode;
        this.message = message;
    }

    public LoginResponse(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }




    public String getJwt() {
        return jwt;
    }
}
