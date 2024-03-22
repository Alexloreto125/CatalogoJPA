package org.example.exceptions;

public class NotFoundExceptionYear extends RuntimeException {
    public NotFoundExceptionYear(Integer id) {
        super("Il record con Anno" + id + " non è stato trovato!");
    }


}
