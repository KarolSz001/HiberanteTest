package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "subjects")
public class Subject {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer ects;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Exam> exams;
}
