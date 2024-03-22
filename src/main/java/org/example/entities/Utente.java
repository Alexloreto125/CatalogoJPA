package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.DateFormat;
import java.util.UUID;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue
    private UUID tessera;     //! PRIMARY KEY


    private String name;
    private String surname;
    private String Birthday;


    public Utente( String name, String surname, String birthday) {
        this.name = name;
        this.surname = surname;
        Birthday = birthday;
    }

    public UUID getTessera() {
        return tessera;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}
