package com.app.service;


import com.app.dto.AppExamDTO;
import com.app.exception.AppException;
import com.app.model.Exam;

import com.app.repository.ExamRepository;
import com.app.repository.StudentRepository;
import com.app.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


// w main powstawiac kilkanascie examinow
// na potrzeby interview zaloz ze masz 4 studentow i 3 przedmioty i wstawiaj tak zeby kazdy student odbyl egzamin z danego
// przedmiotu

// b. metoda zwraca tych liste studentow, ktorzy zaliczyli przedmiot o nazwie podanej jako argument

// c. metoda zwraca trzech studentow, ktorzy najwczesniej zdali swoje egzaminy

// d. metoda zwraca tych studentow ktorzy ze wszystkich zdanych egzaminow uzyskli lacznie sume punktow ects wieksza niz
//    podana jako argument metody

// e. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia ilosc osob, ktore zaliczyly ten przedmiot

// f. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia obiket Student ktory reprezentuje studenta
//    ktory uzyskal najlepszy wynik z tego przedmiotu

// g. metoda zwraca liste tych studentow, ktorzy zaliczyli egzamin o podanej jako argument metody nazwie w przedziale
//    dat <dateFrom, dateTo> ktore to daty tez podasz jako argument
@RequiredArgsConstructor
public class AppService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;

    // a. dodawanie do bazy exam - metoda przyjmie jako argument AddExamDto, sprawdza czy w bazie jest student o takim imieniu
// oraz przedmiot o takiej nazwie jak podano w DTO - jezeli nie to wyjatek a jezeli sa to ich pobiera i pozniej wstawia
// Exam ktory bedzie z nimi w relacji

    public Exam addExamToDb(AppExamDTO appExamDTO) {

        if (appExamDTO == null) {
            throw new AppException("object is null");
        }

        var student = studentRepository
                .findByName(appExamDTO.getStudentName())
                .orElseThrow(() -> new AppException("NO FOUND STUDENT WITH THIS NAME"));

        var subject = subjectRepository
                .findByName(appExamDTO.getSubjectName())
                .orElseThrow(() -> new AppException("NO FOUND SUBJECT WITH THIS NAME"));

        Exam exam = Exam.builder().student(student).subject(subject).date(appExamDTO.getDate()).grade(appExamDTO.getGrade()).build();
        var insertExam = examRepository
                .addOrUpdate(exam)
                .orElseThrow(() -> new AppException("cannot insert exam"));

        return insertExam;
    }


// b. metoda zwraca tych liste studentow, ktorzy zaliczyli przedmiot o nazwie podanej jako argument

    public void solutionB(String subjectName) {


        if (subjectName == null) {
            throw new AppException(" null arg");
        }

        var subject = subjectRepository
                .findByName(subjectName)
                .orElseThrow(() -> new AppException("NO FOUND SUBJECT WITH THIS NAME IN DB"));

        System.out.println("\n metoda zwraca tych liste studentow, ktorzy zaliczyli przedmiot o nazwie podanej jako argument");
        studentRepository.findBySubjectNameAndPass(subjectName);


    }
//    c. metoda zwraca trzech studentow, ktorzy najwczesniej zdali swoje egzaminy

    public void solutionC(){
        System.out.println("metoda zwraca trzech studentow, ktorzy najwczesniej zdali swoje egzaminy");
        studentRepository.findThreeStudentHowPassedEarlyExam();
    }
    //// d. metoda zwraca tych studentow ktorzy ze wszystkich zdanych egzaminow uzyskli lacznie sume punktow ects wieksza niz
    ////    podana jako argument metody

    public void solutionD(Integer number){
        System.out.println(" metoda zwraca tych studentow ktorzy ze wszystkich zdanych egzaminow uzyskli lacznie sume punktow ects wieksza niz\n" +
                "    ////    podana jako argument metody");
        studentRepository.findMostSmartStudents(number);
    }
// e. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia ilosc osob, ktore zaliczyly ten przedmiot

    public void solutionE(){
        studentRepository.findMapE();
    }
// f. metoda zwraca mape, w ktorej kluczem jest nazwa przedmiotu a wartoscia obiket Student ktory reprezentuje studenta
//    ktory uzyskal najlepszy wynik z tego przedmiotu


    public void solutionF(){
        studentRepository.findMap2F();
    }

    // // g. metoda zwraca liste tych studentow, ktorzy zaliczyli egzamin o podanej jako argument metody nazwie w przedziale
    //    //    dat <dateFrom, dateTo> ktore to daty tez podasz jako argument

    public void solutionG(String name, LocalDate dataFrom, LocalDate dataTo){
        studentRepository.findMap2G(name,dataFrom,dataTo);
    }
}
