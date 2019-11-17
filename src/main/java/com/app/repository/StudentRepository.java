package com.app.repository;

import com.app.model.Student;
import com.app.repository.generic.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Optional<Student> findByName(String name);
    void findBySubjectNameAndPass(String subjectName);
    void findThreeStudentHowPassedEarlyExam();
    void findMostSmartStudents(Integer item);
    void findMapE();
    void findMap2F();
    void findMap2G(String name, LocalDate dataFrom, LocalDate dataTo);

}
