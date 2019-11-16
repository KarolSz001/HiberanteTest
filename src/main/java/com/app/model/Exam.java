package com.app.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

// Exam -> id, grade, date, student, subject
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "exams")

public class Exam {

    @Id
    @GeneratedValue
    private Long id;

    private Double grade;
    private LocalDate date;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private Subject subject;





}
