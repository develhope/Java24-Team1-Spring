package com.develhope.spring.exceptions;

public class CourseException extends Exception {

        private int internalCodeError;

        public CourseException(String message, int internalCodeError) {
            super(message);
            this.internalCodeError = internalCodeError;
        }
    }


