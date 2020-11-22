package ru.ssau.tk._KEPA_._practice_.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    private static final long serialVersionUID = -3939411084773782479L;

    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
