package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.Prestito;
import org.example.exceptions.NotFoundExceptionPrestito;

import java.util.List;
import java.util.UUID;

public class PrestitoDao {

    private EntityManager em;

    public PrestitoDao(EntityManager em){
        this.em= em;

    }

    public void savePrestito(Prestito prestito){
        try {



            if ( !checkDisp(prestito.getIsbn())){
                System.out.println(" Libro non disponibile ");

            }
            else {

                EntityTransaction transaction = em.getTransaction();

                transaction.begin();

                em.persist(prestito);
                transaction.commit();


                System.out.println("Prestito salvato");

                System.out.println("ID DEL PRESTITO: "+ prestito.getId_prestito());

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Prestito findPrestito(long id_prestito){

        Prestito prestito= em.find(Prestito.class, id_prestito);

        if(prestito == null) {
            throw new NotFoundExceptionPrestito(id_prestito);
        }

        return prestito;


    }

    public boolean checkDisp(String isbn){
        TypedQuery<Prestito> queryDisp = em.createQuery("SELECT d FROM Prestito d WHERE d.isbn = :isbn AND d.dataRestituzioneEffettiva IS NULL", Prestito.class);
        queryDisp.setParameter("isbn", isbn);
        List<Prestito> prestitiNonRestituiti = queryDisp.getResultList();
        return prestitiNonRestituiti.isEmpty();
    }

    public List<Prestito> checkElementiInPrestito(String tessera){
        TypedQuery<Prestito> queryDisp = em.createQuery("SELECT d FROM Prestito d WHERE d.tessera = :tessera AND d.dataRestituzioneEffettiva IS NULL", Prestito.class);
        queryDisp.setParameter("tessera", tessera);
        List<Prestito> elementiInPrestito = queryDisp.getResultList();
        return elementiInPrestito ;
    }


    public List<Prestito> elementiScaduti(){
        TypedQuery<Prestito> queryDisp = em.createQuery("SELECT d FROM Prestito d WHERE d.dataRestituzioneEffettiva IS NULL", Prestito.class);
        List<Prestito> elementiDaRestituire = queryDisp.getResultList();
        return elementiDaRestituire ;
    }
}
