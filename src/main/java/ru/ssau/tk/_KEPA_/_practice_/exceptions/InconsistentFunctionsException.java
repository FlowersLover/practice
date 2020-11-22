package ru.ssau.tk._KEPA_._practice_.exceptions;

public class InconsistentFunctionsException extends RuntimeException {

    private static final long serialVersionUID = -7681829952992166555L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}

