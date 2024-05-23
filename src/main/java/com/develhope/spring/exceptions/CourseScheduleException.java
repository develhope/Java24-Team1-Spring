package com.develhope.spring.exceptions;

public class CourseScheduleException extends Exception{

    private int internalCodeError;

    public CourseScheduleException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
