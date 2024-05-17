package com.develhope.spring.exceptions;

public class ReviewException extends Exception{

    private int internalCodeError;

    public ReviewException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
