package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "prestito")
public class Prestito {


    @Id
    @GeneratedValue
    private long id_prestito;

    private String isbn;
    private String tessera;

    private LocalDate dataInizio;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;

public Prestito(){

}
    public Prestito(String isbn, String tessera, LocalDate dataInizio, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.isbn = isbn;
        this.tessera = tessera;
        this.dataInizio = dataInizio;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public long getId_prestito() {
        return id_prestito;
    }

    public void setId_prestito(String id_prestito) {
        this.id_prestito = Long.parseLong(id_prestito);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTessera() {
        return tessera;
    }

    public void setTessera(String tessera) {
        this.tessera = tessera;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }


    @Override
    public String toString() {
        return "Prestito{" +
                "id_prestito=" + id_prestito +
                ", isbn='" + isbn + '\'' +
                ", tessera='" + tessera + '\'' +
                ", dataInizio=" + dataInizio +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
