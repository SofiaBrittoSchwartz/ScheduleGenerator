package org.sofia.generator;

// if want to make people have to deal with this exception, extend Exception instead of RuntimeException
public class ShiftException extends RuntimeException {
    private final int errorCode;

    ShiftException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
