package com.app.model;

// Student -> id, name, age
// Subject -> id, name, ects
// Exam -> id, grade, date, student, subject

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Exam> exams;

}
