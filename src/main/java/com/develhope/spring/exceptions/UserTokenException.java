package com.develhope.spring.exceptions;

public class UserTokenException extends Exception{
    private int internalCodeError;

    public UserTokenException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
