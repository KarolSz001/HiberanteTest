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
 *
 */
public class App 
{
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
    public static void main( String[] args )
    {

        var studentRepo = new StudentRepositoryImpl("HBN");
        var examRepo = new ExamRepositoryImpl("HBN");
        var subjectRepo = new SubjectRepositoryImpl("HBN");

        var appService = new AppService(studentRepo,subjectRepo,examRepo);
        var student = Student.builder().name("KAROL").age(30).build();
        var subject = Subject.builder().name("MATH").ects(100).build();

        studentRepo.addOrUpdate(student);
        subjectRepo.addOrUpdate(subject);


        AppExamDTO appExamDTO = AppExamDTO.builder().studentName("KAROL").subjectName("MATH").date(LocalDate.of(2019,10,12)).grade(6.0).build();

        System.out.println(appService.addExamToDb(appExamDTO));


    }
}
