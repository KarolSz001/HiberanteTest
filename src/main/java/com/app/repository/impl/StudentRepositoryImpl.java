package com.app.repository.impl;

import com.app.model.Student;
import com.app.repository.StudentRepository;
import com.app.repository.generic.AbstractCrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class StudentRepositoryImpl extends AbstractCrudRepository<Student, Long> implements StudentRepository {

    public StudentRepositoryImpl(String persistenceUnit) {
        super(persistenceUnit);
    }

    @Override
    public Optional<Student> findByName(String name) {
        EntityManager em = null;
        EntityTransaction tx = null;

        Optional<Student> student = Optional.empty();


        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            student = em
                    .createQuery("select s from Student s where s.name = :name", Student.class)
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

        return student;
    }
}
