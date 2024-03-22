package org.example.exceptions;

import java.util.UUID;

public class NotFoundExceptionAutore extends RuntimeException{

    public NotFoundExceptionAutore(String autore) {
        super("Il record con Autore " + autore + " non è stato trovato!");
    }



}