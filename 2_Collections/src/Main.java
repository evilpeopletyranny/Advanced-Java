import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        // Создание LinkedList для хранения целых чисел
        List<Integer> numbers = new LinkedList<>();

        // Добавление элементов
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        // Добавление элемента в начало списка
        numbers.add(0, 5); // Вставляет 5 на позицию 0

        // Добавление элемента в конец списка
        numbers.add(numbers.size(), 50); // Вставляет 50 в конец

        // Отображение списка
        System.out.println("Numbers List: " + numbers);

        // Доступ к элементу по индексу
        int num = numbers.get(3);
        System.out.println("Number at index 3: " + num); // Вывод: 30

        // Изменение элемента
        numbers.set(2, 25); // Заменяет 20 на 25
        System.out.println("Updated Numbers List: " + numbers);

        // Удаление элемента
        numbers.remove(Integer.valueOf(40)); // Удаляет первый экземпляр 40
        System.out.println("After Removal: " + numbers);

        // Итерация по списку с помощью for-each
        System.out.println("\nIterating through the list:");
        for (
                int number : numbers) {
            System.out.println(number);
        }
    }

}