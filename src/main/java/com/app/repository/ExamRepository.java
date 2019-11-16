package com.app.repository;

import com.app.model.Exam;
import com.app.repository.generic.CrudRepository;

import java.util.Optional;

public interface ExamRepository extends CrudRepository<Exam,Long>  {

}
