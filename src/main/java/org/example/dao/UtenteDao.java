package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.Utente;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em){
        this.em= em;

    }

    public void saveUtente(Utente user){
        try {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.persist(user);
            transaction.commit();
            System.out.println(user.getName()+ " "+ user.getSurname()+ " con tessera "+ user.getTessera());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
