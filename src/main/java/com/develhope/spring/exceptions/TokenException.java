package com.develhope.spring.exceptions;

public class TokenException extends Exception{
    private int internalCodeError;

    public TokenException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
