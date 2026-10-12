package ua.opnu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    @Test
    void polymorphicToString() {
        Person[] people = {
                new Person("Петренко", "Іван", 30),
                new Student("Бондар", "Марія", 18, "АІ-254", "ST1002"),
                new Lecturer("Мельник", "Олег", 45, "математики", 26000)
        };
        assertEquals("Людина Петренко Іван, вік: 30", people[0].toString());
        assertEquals("Студент групи АІ-254, Бондар Марія, вік: 18. Номер студентського квитка: ST1002",
                people[1].toString());
        assertEquals("Викладач кафедри математики, Мельник Олег, вік: 45. Зарплата: 26000.0",
                people[2].toString());
    }

    @Test
    void gettersAndSetters() {
        Student student = new Student("Бондар", "Марія", 18, "АІ-254", "ST1002");
        student.setLastName("Шевченко");
        student.setFirstName("Андрій");
        student.setAge(19);
        student.setGroup("АІ-253");
        student.setStudentId("ST2001");
        assertEquals("Шевченко", student.getLastName());
        assertEquals("Андрій", student.getFirstName());
        assertEquals(19, student.getAge());
        assertEquals("АІ-253", student.getGroup());
        assertEquals("ST2001", student.getStudentId());

        Lecturer lecturer = new Lecturer("Мельник", "Олег", 45, "математики", 26000);
        lecturer.setDepartment("інформатики");
        lecturer.setSalary(28000);
        assertEquals("інформатики", lecturer.getDepartment());
        assertEquals(28000, lecturer.getSalary(), 0.000001);
    }
}
