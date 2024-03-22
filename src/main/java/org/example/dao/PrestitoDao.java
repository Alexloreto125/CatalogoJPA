package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Prestito;
import org.example.exceptions.NotFoundExceptionPrestito;

public class PrestitoDao {

    private EntityManager em;

    public PrestitoDao(EntityManager em){
        this.em= em;

    }

    public void savePrestito(Prestito prestito){
        try {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.persist(prestito);
            transaction.commit();


            System.out.println("Prestito salvato");

            System.out.println("ID DEL PRESTITO: "+ prestito.getId_prestito());
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

}
