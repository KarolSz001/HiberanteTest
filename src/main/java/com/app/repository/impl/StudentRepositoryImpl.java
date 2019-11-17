package com.app.repository.impl;

import com.app.model.Exam;
import com.app.model.Student;

import com.app.model.Subject;
import com.app.repository.StudentRepository;
import com.app.repository.generic.AbstractCrudRepository;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sound.midi.Soundbank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import java.util.stream.Collectors;

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

    public void findBySubjectNameAndPass(String subjectName) {
        EntityManager em = null;
        EntityTransaction tx = null;


        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s from Student s join s.exams e where e.grade > 2.0 and e.subject.name = :name   ", Student.class)
                    .setParameter("name", subjectName)
                    .getResultList()
                    .forEach(System.out::println);

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

    }

    public void findThreeStudentHowPassedEarlyExam() {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s, e.date from Student s join s.exams e order by e.date", Object[].class)
                    .getResultList()
                    .stream()
                    .limit(3)
                    .forEach(row -> System.out.println(row[0] + ":::::" + row[1]));

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

    }

    public void findMostSmartStudents(Integer ectsNumber) {
//        metoda zwraca tych studentow ktorzy ze wszystkich zdanych egzaminow uzyskli lacznie sume punktow ects wieksza niz\n" +
//          podana jako argument metody"
        EntityManager em = null;
        EntityTransaction tx = null;
        List<Student> students = null;

        try {

            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s, e.subject from Student s join s.exams e where e.grade > 2.0 ", Object[].class)
                    .getResultList()
//                    .forEach((row)-> System.out.println(row[0] + "::::" + row[1] ));
            .stream()

                    .collect(Collectors.groupingBy(row->row[0]))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e->(Student)e.getKey(),
                            e->(List<Object>)e.getValue().stream().map(m->m[1]).collect(Collectors.toList())
                    ))
                    .entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()).forEach(System.out::println);




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
    }

    // e. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia ilosc osob, ktore zaliczyly ten przedmiot

    public void findMapE() {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<Student> students = null;

        try {

            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s, e.subject.name from Student s join s.exams e where e.grade > 2.0 ", Object[].class)
                    .getResultList()
//                   .forEach(row -> System.out.println(row[0] + " :::: " + row[1]));
                    .stream()
                    .collect(Collectors.groupingBy(row->row[1]))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e->(String)e.getKey(),
                            e->e.getValue().size()
                    ))
                    .forEach((k,v)-> System.out.println(k + "::::::::" + v));

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
    }
    // f. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia obiket Student ktory reprezentuje studenta
//    ktory uzyskal najlepszy wynik z tego przedmiotu
    @Override
    public void findMap2F() {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<Student> students = null;

        try {

            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s.name, e.student, e.grade from Subject s join s.exams e", Object[].class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.groupingBy(row->row[0]))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e->(String)e.getKey(),
                            e->(List<Object[]>)e.getValue()
                            )).forEach((k,v)-> System.out.println(k + "::::::::" + v));

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

    }

//    metoda zwraca liste tych studentow, ktorzy zaliczyli egzamin o podanej jako argument metody nazwie w przedziale
    //    dat <dateFrom, dateTo> ktore to daty tez podasz jako argument
    public void findMap2G(String name, LocalDate dateFrom, LocalDate dateTo){

        EntityManager em = null;
        EntityTransaction tx = null;

        try {

            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em
                    .createQuery("select s from Student s join s.exams e where e.grade > 2.0 and e.date > :dateFrom and e.date < :dateTo and e.subject.name = :name", Object[].class)
                    .setParameter("name",name)
                    .setParameter("dateFrom",dateFrom)
                    .setParameter("dateTo",dateTo)
                    .getResultList()
                    .forEach(System.out::println);

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

    }
}
