package com.app.repository;

import com.app.model.Student;
import com.app.repository.generic.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Optional<Student> findByName(String name);

}
