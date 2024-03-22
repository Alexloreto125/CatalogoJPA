package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.CollezioneEditoriale;
import org.example.entities.Libri;
import org.example.entities.Periodicita;
import org.example.entities.Riviste;

import java.util.HashMap;
import java.util.Map;

public class CatalogoDAO {
    private EntityManager em;

    public CatalogoDAO(EntityManager em){
        this.em= em;

    }


    public void save(CollezioneEditoriale elemento){

        try {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.persist(elemento);
            transaction.commit();

            if (elemento instanceof Libri){
                Libri libro= (Libri) elemento;
            System.out.println(libro.getTitolo()+ " "+ libro.getAutore()+ " genere "+ libro.getGenere());
            }else if (elemento instanceof Riviste){
                Riviste riviste= (Riviste) elemento;
            System.out.println(elemento.getTitolo()+ " "+elemento.getNumberOfPages() +" "+ ((Riviste) elemento).getPeriodicita() );
            }




        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
