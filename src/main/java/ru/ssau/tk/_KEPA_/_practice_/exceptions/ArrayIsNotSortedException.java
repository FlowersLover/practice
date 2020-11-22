package ru.ssau.tk._KEPA_._practice_.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = -49246185403223778L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
