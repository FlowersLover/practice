package ru.ssau.tk._KEPA_._practice_.exceptions;

public class FunctionAreNotSimilarException extends RuntimeException {
    private static final long serialVersionUID = -5544690408761188450L;

    public FunctionAreNotSimilarException(){
    }

    public FunctionAreNotSimilarException(String message){
        super(message);
    }
}