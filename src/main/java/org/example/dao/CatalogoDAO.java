package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.entities.CollezioneEditoriale;
import org.example.entities.Libri;
import org.example.entities.Riviste;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotFoundExceptionAutore;
import org.example.exceptions.NotFoundExceptionYear;

import java.util.List;
import java.util.UUID;

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

    public CollezioneEditoriale findByIsbn(UUID isbn){

        CollezioneEditoriale elemento = em.find(CollezioneEditoriale.class, isbn);
        if(elemento == null) throw new NotFoundException(isbn);
        return elemento;
//        return em.find(Libri.class, isbn);

    }


    public void findElementoByIsbnDelete(UUID isbn){
        EntityTransaction transaction = em.getTransaction();
        // Quando si parla di operazioni che vanno a modificare il db come save, update, delete devo usare le transazioni
        transaction.begin();
        Query deleteQuery = em.createQuery("DELETE FROM CollezioneEditoriale a WHERE a.isbn = :isbn"); // DELETE FROM animals WHERE name = 'nomedacancellare'
        deleteQuery.setParameter("isbn", isbn);
        int numeroElementiCancellati = deleteQuery.executeUpdate(); // Le query di cancellazione e le query di aggiornamento sono query che modificano il db, pertanto hanno un loro metodo dedicato executeUpdate
        transaction.commit();
        System.out.println("Numero di elementi cancellati: " + numeroElementiCancellati);

    }

    public CollezioneEditoriale findByAnno(Integer yearOfPublication){
        TypedQuery<CollezioneEditoriale> query = em.createQuery("SELECT e FROM CollezioneEditoriale e WHERE e.yearOfPublication = :year", CollezioneEditoriale.class);
        query.setParameter("year", yearOfPublication);
        List<CollezioneEditoriale> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            throw new NotFoundExceptionYear(yearOfPublication);
        }
        return resultList.get(0);
//        return em.find(Libri.class, isbn);

    }


    public List<CollezioneEditoriale> findByAutore(String autore){
        TypedQuery<CollezioneEditoriale> query = em.createQuery("findByAutore", CollezioneEditoriale.class);
        query.setParameter("autore",autore);
        return query.getResultList();
    }


    public List<CollezioneEditoriale> findByNameStartsWith(String partialName){
        TypedQuery<CollezioneEditoriale> query = em.createQuery("SELECT a FROM CollezioneEditoriale a WHERE LOWER(a.titolo) LIKE LOWER(:partialName)", CollezioneEditoriale.class);
        // LOWER ci consente di emulare il comportamento dell'ILIKE
        query.setParameter("partialName", partialName + "%");
        return query.getResultList();
    }
}








