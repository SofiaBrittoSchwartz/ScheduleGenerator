package org.sofia.generator.core;

// if want to make people have to deal with this exception, extend Exception instead of RuntimeException
public class ShiftException extends RuntimeException {
    //TODO: Convert this into an ENUM of errors
    private final int errorCode;

    public ShiftException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
