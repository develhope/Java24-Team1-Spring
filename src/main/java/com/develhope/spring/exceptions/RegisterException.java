package com.develhope.spring.exceptions;

public class RegisterException extends Exception{
    private int internalCode;

    public RegisterException(String message, int internalCode) {
        super(message);
        this.internalCode = internalCode;
    }

}
