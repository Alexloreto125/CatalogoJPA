package org.example.entities;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "Catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_elemento")
//@Table(name = "CollezioneEditoriale")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CollezioneEditoriale {

    @Id
    @GeneratedValue
    public UUID isbn;

    public String titolo;
    private int numberOfPages;
    private int yearOfPublication;

    public CollezioneEditoriale( String titolo, int numberOfPages, int yearOfPublication) {

        this.titolo = titolo;
        this.numberOfPages = numberOfPages;
        this.yearOfPublication = yearOfPublication;
    }



    public UUID getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = UUID.fromString(isbn);
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }


}
