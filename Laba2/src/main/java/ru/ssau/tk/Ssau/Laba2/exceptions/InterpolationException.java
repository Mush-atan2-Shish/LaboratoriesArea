package ru.ssau.tk.Ssau.Laba2.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5479335392755749232L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
