import dao.StudentCRUDOperations;
import entity.Criteria;
import entity.Sex;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student mitantsoa = new Student("Mitantsoa", Sex.MALE, LocalDate.of(2003,07,06));
        Student newMitantsoa = new Student("Mitantsoa Allan", Sex.MALE, LocalDate.of(2003,07,06));
        Student mahery = new Student("Mahery", Sex.MALE, LocalDate.of(2003,8,20));
        StudentCRUDOperations studentCRUDOperations = new StudentCRUDOperations();
//        studentCRUDOperations.saveAll(List.of(mahery));
//        studentCRUDOperations.update(1,newMitantsoa);
//        studentCRUDOperations.delete(2);
        Criteria criteria = new Criteria("sex", "MALE");
        System.out.println(studentCRUDOperations.findByCriteria(criteria));
    }
}