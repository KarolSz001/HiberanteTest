package com.app.repository;

import com.app.model.Subject;
import com.app.repository.generic.CrudRepository;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
}
