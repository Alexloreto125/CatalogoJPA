package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.CatalogoDAO;
import org.example.dao.LibriDao;
import org.example.dao.UtenteDao;
import org.example.entities.Libri;
import org.example.entities.Periodicita;
import org.example.entities.Riviste;
import org.example.entities.Utente;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoJpa");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UtenteDao ud= new UtenteDao(em);

        Utente aldo= new Utente("Aldo","Baglio","30/12/2003");
        Utente giovanni= new Utente("Giovanni","Storti","30/12/2005");

        ud.saveUtente(giovanni);
        ud.saveUtente(aldo);

        CatalogoDAO cd= new CatalogoDAO(em);

        Libri caccia= new Libri("caccia","horror","Enrich",104,1104);
        Riviste ss= new Riviste("SS",10,1925, Periodicita.settimanale);


        cd.save(caccia);
        cd.save(ss);


        System.out.println("EGFFE");
    }
}
