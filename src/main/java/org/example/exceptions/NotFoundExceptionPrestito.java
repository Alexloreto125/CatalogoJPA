package org.example.exceptions;

import java.util.UUID;

public class NotFoundExceptionPrestito extends RuntimeException{
    public NotFoundExceptionPrestito(long id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}
