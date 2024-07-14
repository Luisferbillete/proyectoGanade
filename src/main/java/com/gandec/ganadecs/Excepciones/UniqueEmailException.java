package com.gandec.ganadecs.Excepciones;

public class UniqueEmailException extends UniqueConstraintException{
    public UniqueEmailException(String message) {
        super(message);
    }
}
