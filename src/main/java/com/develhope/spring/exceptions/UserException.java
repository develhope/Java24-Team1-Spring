package com.develhope.spring.exceptions;

public class UserException extends Exception{

    private int internalCodeError;

    public UserException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
