# Generics Wildcard

**Wildcard** (подстановочный знак) в Java Generics — это специальный символ ```?```, который используется для
представления
неизвестного типа. Wildcards позволяют создать более гибкие и обобщенные методы, классы или интерфейсы, работающие с
различными типами данных. Они часто применяются для создания обобщенных коллекций, когда точный тип элементов неизвестен
или может варьироваться.

## Виды Wildcards

1. Неограниченный Wildcard (```?```)
2. Ограниченный сверху Wildcard (```? extends T```)
3. Ограниченный снизу Wildcard (```? super T```)

## 1. Неограниченный Wildcard

Неограниченный wildcard (```?```) используется, когда нас не интересует конкретный тип параметра, но необходимо работать
с обобщенным типом. Чаще всего применяется, когда метод должен работать с обобщенным типом, но не изменяет данные.

### Пример

```java
import java.util.List;

public class WildcardExample {
    // Метод, который принимает список любого типа
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.println(elem);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        List<String> strList = List.of("one", "two", "three");

        printList(intList); // Вывод: 1, 2, 3
        printList(strList); // Вывод: one, two, three
    }
}
```

### Когда использовать

- Когда метод не зависит от типа элементов в коллекции и просто обрабатывает их без изменения.
- Для чтения данных, не уточняя тип элементов.

## 2. Неограниченный Wildcard

Ограничение сверху (```? extends T```) указывает, что параметр типа должен быть типом ```T``` или его подклассом.
Чаще всего используется для чтения данных из коллекции, так как позволяет работать с элементами типа T и его подтипами,
но добавление в коллекцию ограничено из-за неизвестности точного типа.

### Пример

```java
import java.util.List;

public class WildcardExample {
    // Метод для суммирования чисел, работает с любым типом, наследующим Number
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);

        System.out.println(sumOfList(intList));    // Вывод: 6.0
        System.out.println(sumOfList(doubleList)); // Вывод: 6.6
    }
}
```

### Когда использовать

- Когда нужно **безопасно** читать данные из коллекции.
- Для методов, работающих с элементами типа ```T``` и его подтипов.

## 3. Ограниченный снизу Wildcard

Ограничение снизу (```? super T```) указывает, что параметр типа должен быть типом ```T``` или его суперклассом.
Применяется, когда нужно **безопасно** добавлять данные в коллекцию. Чтение элементов ограничено типом ```Object```, так
как неизвестен точный тип элемента.

### Пример

```java
import java.util.ArrayList;
import java.util.List;

public class WildcardExample {
    // Метод для добавления элементов в список, который принимает Integer и его суперклассы
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void main(String[] args) {
        List<Number> numList = new ArrayList<>();
        addNumbers(numList);
        System.out.println(numList); // Вывод: [1, 2, 3]

        List<Object> objList = new ArrayList<>();
        addNumbers(objList);
        System.out.println(objList); // Вывод: [1, 2, 3]
    }
}
```