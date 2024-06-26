package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.CatalogoDAO;
import org.example.dao.LibriDao;
import org.example.dao.PrestitoDao;
import org.example.dao.UtenteDao;
import org.example.entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoJpa");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        CatalogoDAO cd = new CatalogoDAO(em);
        PrestitoDao prestito= new PrestitoDao(em);

        System.out.println("Aggiungi elemento");

        System.out.println("Tipo :");
        System.out.println("1: Aggiungi Libro");
        System.out.println("2: Aggiungi Riviste");
        System.out.println("3: Ricerca per ISBN");
        System.out.println("4: Ricerca per Anno");
        System.out.println("5: Ricerca per Titolo");
        System.out.println("6: Ricerca per Autore");
        System.out.println("7: Elimina elemento");
        System.out.println("8: Aggiungi Prestito");
        System.out.println("9: Controlla Prestiti Scaduti");
        System.out.println("10: Controlla Prestiti Scaduti Utente");

        int tipo = Integer.parseInt(scanner.nextLine());
        switch (tipo) {
            case 1:
                System.out.println("Inserisci i dettagli del libro:");
                System.out.println("Titolo:");
                String titolo = scanner.nextLine();
                System.out.println("Genere:");
                String genere = scanner.nextLine();
                System.out.println("Autore:");
                String autore = scanner.nextLine();
                System.out.println("Numero di pagine:");
                int numberOfPages = Integer.parseInt(scanner.nextLine());
                System.out.println("Anno di pubblicazione:");
                int yearOfPublication = Integer.parseInt(scanner.nextLine());

                Libri libro = new Libri(titolo, genere, autore, numberOfPages, yearOfPublication);
                cd.save(libro);
                break;

            case 2:
                System.out.println("Inserisci i dettagli della rivista:");
                System.out.println("Titolo:");
                String titoloRivista = scanner.nextLine();
                System.out.println("Numero di pagine:");
                int numberOfPagesRivista = Integer.parseInt(scanner.nextLine());
                System.out.println("Anno di pubblicazione:");
                int yearOfPublicationRivista = Integer.parseInt(scanner.nextLine());
                System.out.println("Periodicita (1 = Settimanale, 2 = Mensile, 3 = Trimestrale) :");
                Periodicita periodicita = null;
                try {


                    int scelta = Integer.parseInt(scanner.nextLine());


                    switch (scelta) {

                        case 1:
                            periodicita = Periodicita.settimanale;
                            break;
                        case 2:
                            periodicita = Periodicita.mensile;
                            break;
                        case 3:
                            periodicita = Periodicita.semestrale;
                            break;
                        default:
                            System.out.println("Opzione non valida, si prega di riprovare.");

                    }


                } catch (NumberFormatException e) {
                    System.out.println("Errore: Inserisci un numero valido!");
                }

                Riviste rivista = new Riviste(titoloRivista, numberOfPagesRivista, yearOfPublicationRivista, periodicita);
                cd.save(rivista);
                break;

            case 3:

                System.out.println("INSERISCI IL CODICE ISBN: ");
                String isbn = scanner.nextLine();

                System.out.println("ELEMENTO CON ISBN: "+cd.findByIsbn(UUID.fromString(isbn)));


                break;

            case 4:

                System.out.println("INSERISCI L'ANNO DI PUBBLICAZIONE");
                int anno= Integer.parseInt(scanner.nextLine());

                System.out.println("RICERCA IN BASO ALL'ANNO: "+ anno+ " ->"+ cd.findByAnno(anno));



                break;
            case 5:
                System.out.println("INSERISCI IL TITOLO");
                String titoloS= scanner.nextLine();

                System.out.println("RICERCA IN BASO AL TITOLO: "+ titoloS+ " ->"+ cd.findByNameStartsWith(titoloS));



                break;
//            case 6:
//                System.out.println("INSERISCI L'AUTORE");
//                String autoreS= scanner.nextLine();
//
//                System.out.println("RICERCA IN BASO ALL'AUTORE: "+ autoreS+ " ->"+ (cd.findByAutore(autoreS)));
//
//
//                break;

            case 7:

                System.out.println("INSERISCI IL CODICE ISBN DA ELIMINARE: ");
                String isbnE = scanner.nextLine();

                cd.findElementoByIsbnDelete(UUID.fromString(isbnE));

                break;

            case 8:
                try{
                System.out.println("INSERISCI IL CODICE ISBN: ");
                String isbnPrestito = scanner.nextLine();

                System.out.println("INSERISCI LA TESSERA: ");
                String tessera= scanner.nextLine();

                System.out.println("INSERISCI DATA DI INIZIO");
                LocalDate dataInizio= LocalDate.parse(scanner.nextLine());

                System.out.println("INSERISCI DATA DI FINE");
                LocalDate dataFine= LocalDate.parse(scanner.nextLine());

                Prestito addPrestito= new Prestito(isbnPrestito,tessera,dataInizio,dataFine, null);


                prestito.savePrestito(addPrestito);

                }catch (DateTimeParseException e) {
                    System.out.println("il formato deve essere anno/mese/giorno");
                }

                break;

            case 9:
                System.out.println("Prestiti scaduti: "+   prestito.elementiScaduti());


                break;

            case 10:

                System.out.println("Inserisci la tessera: ");
                String tessera = scanner.nextLine();
                System.out.println("PRESTITO SCADUTO: " + prestito.checkElementiInPrestito(tessera));

                break;
            case 11:
                System.out.println("ARRIVEDERCI");
                break;

            default:
                System.out.println("Scelta non valida, riprova.");
        }
        while (tipo != 3) ;










//        System.out.println( prestito.findPrestito(102));

//       4





        System.out.println("EGFFE");
    }





}

//        UtenteDao ud= new UtenteDao(em);
//
//        Utente aldo= new Utente("Aldo","Baglio","30/12/2003");
//        Utente giovanni= new Utente("Giovanni","Storti","30/12/2005");
//
//        ud.saveUtente(giovanni);
//        ud.saveUtente(aldo);

//        CatalogoDAO cd= new CatalogoDAO(em);

//        Libri caccia= new Libri("caccia","horror","Enrich",104,1104);
//        Riviste ss= new Riviste("BAMBACLAT",1,2010, Periodicita.mensile);


//        cd.save(caccia);
//        cd.save(ss);





