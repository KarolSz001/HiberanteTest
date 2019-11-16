package com.app.repository.impl;

import com.app.model.Exam;
import com.app.repository.ExamRepository;
import com.app.repository.generic.AbstractCrudRepository;

public class ExamRepositoryImpl extends AbstractCrudRepository<Exam,Long> implements ExamRepository {
    public ExamRepositoryImpl(String persistenceUnit) {
        super(persistenceUnit);
    }
}
