package com.develhope.spring.models;

public class LoginResponse {
    private int code;
    private String jwt;
    private String message;

    public LoginResponse(int code, String message, String jwt) {
        this.code = code;
        this.jwt = jwt;
        this.message = message;
    }

    public LoginResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getJwt() {
        return jwt;
    }
}
