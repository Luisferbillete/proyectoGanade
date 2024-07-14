package com.gandec.ganadecs.Excepciones;

public class UniqueUsernameException extends UniqueConstraintException{
    public UniqueUsernameException(String message) {
        super(message);
    }
}
