package ua.opnu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Завдання 1. TimeSpan");
        TimeSpan empty = new TimeSpan();
        TimeSpan fromMinutes = new TimeSpan(150);
        TimeSpan interval = new TimeSpan(2, 30);
        TimeSpan copy = new TimeSpan(interval);

        System.out.println("Порожній інтервал: " + empty);
        System.out.println("150 хвилин: " + fromMinutes);
        System.out.println("Копія інтервалу: " + copy);

        interval.add(1, 45);
        interval.add(30);
        interval.add(fromMinutes);
        System.out.println("Після додавання: " + interval);

        interval.subtract(1, 15);
        interval.subtract(30);
        interval.subtract(fromMinutes);
        System.out.println("Після віднімання: " + interval);
        System.out.println("У годинах: " + interval.getTotalHours());
        System.out.println("У хвилинах: " + interval.getTotalMinutes());

        interval.scale(2);
        System.out.println("Після множення на 2: " + interval);
        interval.subtract(1000);
        System.out.println("Після спроби відняти 1000 хвилин: " + interval);

        System.out.println("\nЗавдання 2. Успадкування і поліморфізм");
        Person student1 = new Student("Шевченко", "Андрій", 19, "АІ-254", "ST1001");
        Person student2 = new Student("Бондар", "Марія", 18, "АІ-254", "ST1002");
        Person lecturer1 = new Lecturer("Коваленко", "Олена", 38, "інформатики", 24000);
        Person lecturer2 = new Lecturer("Мельник", "Олег", 45, "математики", 26000);

        Person[] people = {
                new Person("Петренко", "Іван", 30),
                student1, student2, lecturer1, lecturer2
        };
        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
