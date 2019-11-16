package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//AddExamDto -> grade, date, studentName, subjectName
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class AppExamDTO {
    private Double grade;
    private LocalDate date;
    private String studentName;
    private String subjectName;




}
