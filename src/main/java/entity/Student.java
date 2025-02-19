package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private Sex sex;
    private LocalDate birthdate;

    public Student(){

    }
    public Student(String name,Sex sex, LocalDate birthdate) {
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthdate=" + birthdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return id == student.id && Objects.equals(name, student.name) && sex == student.sex && Objects.equals(birthdate, student.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, birthdate);
    }
}
