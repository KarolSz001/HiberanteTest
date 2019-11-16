package com.app.service;

import com.app.dto.AppExamDTO;
import com.app.exception.AppException;
import com.app.model.Exam;
import com.app.model.Student;
import com.app.model.Subject;
import com.app.repository.ExamRepository;
import com.app.repository.StudentRepository;
import com.app.repository.SubjectRepository;
import com.app.repository.impl.ExamRepositoryImpl;
import com.app.repository.impl.StudentRepositoryImpl;
import com.app.repository.impl.SubjectRepositoryImpl;
import lombok.RequiredArgsConstructor;


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


    public Exam addExamToDb(AppExamDTO appExamDTO) {
// a. dodawanie do bazy exam - metoda przyjmie jako argument AddExamDto, sprawdza czy w bazie jest student o takim imieniu
// oraz przedmiot o takiej nazwie jak podano w DTO - jezeli nie to wyjatek a jezeli sa to ich pobiera i pozniej wstawia
// Exam ktory bedzie z nimi w relacji


    if(appExamDTO == null){
        throw new AppException("object is null");
    }

    var student = studentRepository
            .findByName(appExamDTO.getStudentName())
            .orElseThrow(()->new AppException("NO FOUND STUDENT WITH THIS NAME"));

    var subject = subjectRepository
            .findByName(appExamDTO.getSubjectName())
            .orElseThrow(()->new AppException("NO FOUND SUBJECT WITH THIS NAME"));

    Exam exam = Exam.builder().student(student).subject(subject).date(appExamDTO.getDate()).grade(appExamDTO.getGrade()).build();
    var insertExam = examRepository
            .addOrUpdate(exam)
            .orElseThrow(()-> new AppException("cannot insert exam"));

    return insertExam;




    }




}
