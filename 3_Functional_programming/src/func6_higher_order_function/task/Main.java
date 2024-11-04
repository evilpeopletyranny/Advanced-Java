package func6_higher_order_function.task;

import java.util.Arrays;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        List<Student> students = Arrays.asList(
//                new Student("Алексей", 21, 4.5, "A1"),
//                new Student("Мария", 19, 4.8, "B1"),
//                new Student("Иван", 22, 3.9, "A1"),
//                new Student("Елена", 20, 4.2, "B1"),
//                new Student("Дмитрий", 23, 4.7, "A1")
//        );
//
//        // a. Отфильтровать студентов старше 20 лет
//        List<Student> olderThan20 = StudentProcessor.filter(students, s -> s.getAge() > 20);
//        System.out.println("Студенты старше 20 лет:");
//        StudentProcessor.forEach(olderThan20, s -> System.out.println(s.getName()));
//
//        // b. Преобразовать список студентов в список их имен
//        List<String> names = StudentProcessor.transform(students, Student::getName);
//        System.out.println("\nИмена студентов:");
//        names.forEach(System.out::println);
//
//        // c. Вывести информацию о каждом студенте на консоль
//        System.out.println("\nИнформация о студентах:");
//        StudentProcessor.forEach(students, s -> System.out.println(
//                s.getName() + ", возраст: " + s.getAge() + ", балл: " + s.getGrade() + ", группа: " + s.getGroup()));
//
//        // d. Найти первого студента с средним баллом выше 4.5
//        Student topStudent = StudentProcessor.findFirst(students, s -> s.getGrade() > 4.5);
//        if (topStudent != null) {
//            System.out.println("\nПервый студент с баллом выше 4.5: " + topStudent.getName());
//        }
//
//        // e. Найти студента с максимальным средним баллом
//        Student bestStudent = StudentProcessor.max(students, (s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()));
//        if (bestStudent != null) {
//            System.out.println("\nСтудент с максимальным баллом: " + bestStudent.getName() + ", балл: " + bestStudent.getGrade());
//        }
//
//        // f. Отсортировать список студентов по имени
//        List<Student> sortedByName = StudentProcessor.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
//        System.out.println("\nСтуденты, отсортированные по имени:");
//        StudentProcessor.forEach(sortedByName, s -> System.out.println(s.getName()));
//    }
}
