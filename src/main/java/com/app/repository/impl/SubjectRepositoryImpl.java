package com.app.repository.impl;

import com.app.model.Subject;
import com.app.repository.SubjectRepository;
import com.app.repository.generic.AbstractCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class SubjectRepositoryImpl extends AbstractCrudRepository<Subject, Long> implements SubjectRepository {
    public SubjectRepositoryImpl(String persistenceUnit) {
        super(persistenceUnit);
    }


    @Override
    public Optional<Subject> findByName(String name) {
        EntityManager em = null;
        EntityTransaction tx = null;

        Optional<Subject> subject = Optional.empty();


        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            subject = em
                    .createQuery("select s from Subject s where s.name = :name", Subject.class)
                    .setParameter("name", name)
                    .getResultStream().findFirst();

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return subject;
    }
}

