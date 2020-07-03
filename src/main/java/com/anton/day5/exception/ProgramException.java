package com.anton.day5.exception;

public class ProgramException extends Exception{ //todo if  exception is thrown in catch - put ex in constructor
    public ProgramException() {
    }

    public ProgramException(String message) {
        super(message);
    }

    public ProgramException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramException(Throwable cause) {
        super(cause);
    }
}
