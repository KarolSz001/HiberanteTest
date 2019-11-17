package com.app;

import com.app.dto.AppExamDTO;
import com.app.model.Exam;
import com.app.model.Student;
import com.app.model.Subject;
import com.app.repository.StudentRepository;
import com.app.repository.impl.ExamRepositoryImpl;
import com.app.repository.impl.StudentRepositoryImpl;
import com.app.repository.impl.SubjectRepositoryImpl;
import com.app.service.AppService;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    // 1. z poprzedniego projektu skopiowac CrudRepo oraz AbstractCrudRepo

    // 2. Utworzyc model bazy:
    // Student -> id, name, age
    // Subject -> id, name, ects
    // Exam -> id, grade, date, student, subject

    // do bazy wstawic kilka przykladowych danych recznie do tabeli students oraz subjects
    // na potrzeby interview zakladamy ze studenci maja unikalne imiona oraz przedmioty maja unikalne nazwy

    // 3. DTO
    // AddExamDto -> grade, date, studentName, subjectName

    // 4. Service

    // a. dodawanie do bazy exam - metoda przyjmie jako argument AddExamDto, sprawdza czy w bazie jest student o takim imieniu
    // oraz przedmiot o takiej nazwie jak podano w DTO - jezeli nie to wyjatek a jezeli sa to ich pobiera i pozniej wstawia
    // Exam ktory bedzie z nimi w relacji

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

    public static void main(String[] args) {

        var studentRepo = new StudentRepositoryImpl("HBN");
        var examRepo = new ExamRepositoryImpl("HBN");
        var subjectRepo = new SubjectRepositoryImpl("HBN");

        var appService = new AppService(studentRepo, subjectRepo, examRepo);


     /*   var student1 = Student.builder().name("KAROL").age(19).build();
        var student2 = Student.builder().name("ADOLF").age(11).build();
        var student3 = Student.builder().name("JON").age(22).build();
        var student4 = Student.builder().name("CONAN").age(22).build();

        studentRepo.addOrUpdate(student1);
        studentRepo.addOrUpdate(student2);
        studentRepo.addOrUpdate(student3);
        studentRepo.addOrUpdate(student4);

        var subject1 = Subject.builder().name("MATH").ects(44).build();
        var subject2 = Subject.builder().name("POL").ects(15).build();
        var subject3 = Subject.builder().name("SEX").ects(67).build();

        subjectRepo.addOrUpdate(subject1);
        subjectRepo.addOrUpdate(subject2);
        subjectRepo.addOrUpdate(subject3);

        var appExamDTO11 = AppExamDTO.builder().studentName("KAROL").subjectName("MATH").date(LocalDate.of(2019, 11, 3)).grade(4.0).build();
        var appExamDTO12 = AppExamDTO.builder().studentName("KAROL").subjectName("POL").date(LocalDate.of(2019, 12, 3)).grade(6.6).build();
        var appExamDTO13 = AppExamDTO.builder().studentName("KAROL").subjectName("SEX").date(LocalDate.of(2019, 9, 3)).grade(6.9).build();
        appService.addExamToDb(appExamDTO11);
        appService.addExamToDb(appExamDTO12);
        appService.addExamToDb(appExamDTO13);

        var appExamDTO21 = AppExamDTO.builder().studentName("ADOLF").subjectName("MATH").date(LocalDate.of(2018, 10, 3)).grade(1.0).build();
        var appExamDTO22 = AppExamDTO.builder().studentName("ADOLF").subjectName("POL").date(LocalDate.of(2019, 1, 3)).grade(5.0).build();
        var appExamDTO23 = AppExamDTO.builder().studentName("ADOLF").subjectName("SEX").date(LocalDate.of(2019, 4, 3)).grade(8.0).build();
        appService.addExamToDb(appExamDTO21);
        appService.addExamToDb(appExamDTO22);
        appService.addExamToDb(appExamDTO23);

        var appExamDTO31 = AppExamDTO.builder().studentName("CONAN").subjectName("MATH").date(LocalDate.of(2018, 10, 5)).grade(1.9).build();
        var appExamDTO32 = AppExamDTO.builder().studentName("CONAN").subjectName("POL").date(LocalDate.of(2019, 1, 23)).grade(5.7).build();
        var appExamDTO33 = AppExamDTO.builder().studentName("CONAN").subjectName("SEX").date(LocalDate.of(2019, 4, 13)).grade(5.0).build();
        appService.addExamToDb(appExamDTO31);
        appService.addExamToDb(appExamDTO32);
        appService.addExamToDb(appExamDTO33);

        var appExamDTO41 = AppExamDTO.builder().studentName("JON").subjectName("MATH").date(LocalDate.of(2018, 11, 5)).grade(1.9).build();
        var appExamDTO42 = AppExamDTO.builder().studentName("JON").subjectName("POL").date(LocalDate.of(2019, 10, 2)).grade(5.7).build();
        var appExamDTO43 = AppExamDTO.builder().studentName("JON").subjectName("SEX").date(LocalDate.of(2017, 4, 2)).grade(5.0).build();
        appService.addExamToDb(appExamDTO41);
        appService.addExamToDb(appExamDTO42);
        appService.addExamToDb(appExamDTO43);*/


//        appService.solutionB("SEX");
//        appService.solutionC();
//        appService.solutionD(40);
//        appService.solutionE();
//        appService.solutionF();
            appService.solutionG("SEX",LocalDate.of(2019,1,1),LocalDate.of(2019,10,1));


    }
}
