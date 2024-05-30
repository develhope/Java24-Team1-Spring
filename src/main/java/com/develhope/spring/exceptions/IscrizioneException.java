package com.develhope.spring.exceptions;

public class IscrizioneException extends Exception {

    private int internalCodeError;

    public IscrizioneException(String message, int internalCodeError) {
        super(message);
        this.internalCodeError = internalCodeError;
    }
}
