package com.develhope.spring.exceptions;

public class GradeException extends Exception{

    private int internalCodeError;

    public GradeException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
