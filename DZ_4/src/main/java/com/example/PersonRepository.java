package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PersonRepository {
    private EntityManagerFactory emf;

    public PersonRepository() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void addPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        em.close();
        return persons;
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
