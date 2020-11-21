package ru.ssau.tk.Ssau.Laba2.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -177530522746757248L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
