# Наследование/реализация generics классов/интерфейсов

## Generics Interface

Всё что было показно на примерах с классами также валидно и для интерфейсов. Интерфейсы также могут быть generic типов.

Объявление обобщённого интерфейса включает параметр типа, который может быть использован в методах интерфейса.

### Примеры:

```java
public interface MyInterface<T> {
    void doSomething(T item);

    T getItem();
}
```

Рассмотрим обобщённый интерфейс ```Container```, который может хранить объект любого типа.

```java
// Container.java
public interface Container<T> {
    void add(T item);

    T get(int index);
}

```

## Наследование generic класса

Наследование обобщённых классов позволяет создавать иерархии классов, которые могут работать с различными типами данных,
сохраняя при этом типовую безопасность.

### [Пример](code%2Fexample1%2FVehicleMain.java) наследования generic класса с заданием типа

Рассмотрим пример, где у нас есть обобщённый базовый класс ```Vehicle```, который работает с типом ```T``` для
идентификатора транспорта. Затем мы создадим дочерний класс ```Car```, который указывает конкретный тип ```String``` для
идентификатора.

```java
public class Vehicle<T> {
    private T id;
    private String name;

    public Vehicle(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        System.out.println("Vehicle ID: " + id + ", Name: " + name);
    }
}
```

- Класс ```Vehicle``` обобщён с параметром типа ```T```.
- Поле ```id``` имеет тип ```T```, позволяя использовать различные типы идентификаторов.
- Метод ```displayInfo()``` выводит информацию о транспортном средстве.

```java
public class Car extends Vehicle<String> {
    private int numberOfDoors;

    public Car(String id, String name, int numberOfDoors) {
        super(id, name);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}
```

- Класс ```Car``` наследует ```Vehicle``` с конкретным типом ```String``` для идентификатора.
- Добавлено поле ```numberOfDoors``` для хранения количества дверей автомобиля.
- Метод ```displayInfo()``` переопределён для вывода дополнительной информации.

```java
public class Main {
    public static void main(String[] args) {
        // Создание экземпляра Vehicle с идентификатором Integer
        Vehicle<Integer> bike = new Vehicle<>(101, "Mountain Bike");
        bike.displayInfo();

        System.out.println();

        // Создание экземпляра Car с идентификатором String
        Car car = new Car("CAR-001", "Sedan", 4);
        car.displayInfo();
    }
}
```

- Создаётся экземпляр ```Vehicle``` с типом идентификатора ```Integer```.
- Создаётся экземпляр ```Car```, наследующий ```Vehicle<String>```, с типом идентификатора ```String```.
- Методы ```displayInfo()``` выводят соответствующую информацию.

### [Пример](code%2Fexample2%2FBoxMain.java) наследования generic класса с сохранением generic типа

Рассмотрим пример, где у нас есть базовый обобщенный класс ```Box<T>```, который представляет контейнер для хранения
объекта типа ```T```. Мы создадим подкласс ```ColorBox<T>```, который расширяет ```Box<T>``` и добавляет
свойство ```color```.

```java
// Базовый обобщенный класс Box
public class Box<T> {
    private T content;

    // Конструктор для инициализации содержимого
    public Box(T content) {
        this.content = content;
    }

    // Геттер для получения содержимого
    public T getContent() {
        return content;
    }

    // Сеттер для изменения содержимого
    public void setContent(T content) {
        this.content = content;
    }

    // Метод для отображения содержимого
    public void displayContent() {
        System.out.println("Box contains: " + content);
    }
}
```

- **Параметр типа ```T```:** Позволяет хранить объект любого типа.
- **Методы ```getContent```, ```setContent```:** Позволяют получать и изменять содержимое коробки.
- **Метод ```displayContent()```:** Отображает содержимое коробки.

```java
// Подкласс ColorBox, наследующий Box и добавляющий свойство color
public class ColorBox<T> extends Box<T> {
    private String color;

    // Конструктор для инициализации содержимого и цвета
    public ColorBox(T content, String color) {
        super(content); // Вызов конструктора родительского класса
        this.color = color;
    }

    // Геттер для получения цвета
    public String getColor() {
        return color;
    }

    // Сеттер для изменения цвета
    public void setColor(String color) {
        this.color = color;
    }

    // Переопределение метода для отображения содержимого с цветом
    @Override
    public void displayContent() {
        System.out.println("ColorBox contains: " + getContent() + " and is " + color);
    }
}
```

- **Наследование:** ```ColorBox<T>``` наследует от ```Box<T>```, сохраняя обобщенный параметр типа ```T```.
- **Дополнительное свойство ```color```:** Добавляет свойство цвета для коробки.
- **Методы** ```getColor```, ```setColor```: Позволяют получать и изменять цвет коробки.
- **Переопределение** ```displayContent()```: Дополняет базовый метод отображением цвета.

```java
// Класс для демонстрации использования Box и ColorBox
public class BoxMain {
    public static void main(String[] args) {
        // Создание экземпляра Box для хранения Integer
        Box<Integer> integerBox = new Box<>(123);
        integerBox.displayContent(); // Вывод: Box contains: 123

        // Создание экземпляра ColorBox для хранения String
        ColorBox<String> stringColorBox = new ColorBox<>("Hello, Generics!", "Red");
        stringColorBox.displayContent(); // Вывод: ColorBox contains: Hello, Generics! and is Red

        // Изменение содержимого и цвета ColorBox
        stringColorBox.setContent("New Content");
        stringColorBox.setColor("Blue");
        stringColorBox.displayContent(); // Вывод: ColorBox contains: New Content and is Blue
    }
}
```

## Реализация generic интерфейса

Реализация Generic интерфейса в Java позволяет классам реализовывать интерфейсы с обобщенными типами. Это обеспечивает
возможность создания гибких и переиспользуемых интерфейсов, которые могут работать с различными типами данных.

### Пример

Рассмотрим пример, где мы создаем обобщенный интерфейс ```Transformer<T, R>```, который определяет метод transform,
преобразующий объект типа T в объект типа R. Затем мы создадим класс StringToIntegerTransformer, который реализует этот
интерфейс для преобразования типа ```T``` в тип ```R```.

```java
// Обобщенный интерфейс Transformer
public interface Transformer<T, R> {
    /**
     * Метод для преобразования объекта типа T в объект типа R
     * @param input Входной объект типа T
     * @return Преобразованный объект типа R
     */
    R transform(T input);
}
```

- Параметры типа ```T``` и ```R```: Определяют тип входных данных и тип результата преобразования.
- Метод ```transform(T input)```: Определяет контракт для преобразования объектов одного типа в другой.

```java
// Класс, реализующий Transformer для преобразования String в Integer
public class StringToIntegerTransformer implements Transformer<String, Integer> {

    /**
     * Преобразует строку в целое число
     * @param input Входная строка
     * @return Целое число, полученное из строки
     */
    @Override
    public Integer transform(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Integer transformation: " + input);
            return null;
        }
    }
}
```

- Реализует ```Transformer<String, Integer>```.
- Преобразует строки в целые числа с обработкой исключений.

```java
// Класс, реализующий Transformer для преобразования Double в String
public class DoubleToStringTransformer implements Transformer<Double, String> {

    /**
     * Преобразует число с плавающей точкой в строку с форматированием
     * @param input Входное число типа Double
     * @return Отформатированная строка
     */
    @Override
    public String transform(Double input) {
        return String.format("Formatted Double: %.2f", input);
    }
}
```

- Реализует ```Transformer<Double, String>```.
- Преобразует числа с плавающей точкой в отформатированные строки.

```java
// Класс, реализующий Transformer для преобразования Integer в String
public class IntegerToStringTransformer implements Transformer<Integer, String> {
    /**
     * Преобразует целое число в строку
     * @param input Входное целое число
     * @return Строковое представление числа
     */
    @Override
    public String transform(Integer input) {
        return "Number: " + input;
    }
}
```

- Реализует ```Transformer<Integer, String>```.
- Преобразует целые числа в строки с добавлением префикса.

```java
// Класс для демонстрации использования Transformer интерфейса
public class TransformerMain {
    public static void main(String[] args) {
        // Создание экземпляров трансформеров
        Transformer<String, Integer> stringToInteger = new StringToIntegerTransformer();
        Transformer<Integer, String> integerToString = new IntegerToStringTransformer();
        Transformer<Double, String> doubleToString = new DoubleToStringTransformer();

        // Преобразование String в Integer
        String numberStr = "123";
        Integer number = stringToInteger.transform(numberStr);
        System.out.println("Transformed String to Integer: " + number); // Вывод: Transformed String to Integer: 123

        // Попытка преобразования некорректной строки в Integer
        String invalidStr = "abc";
        Integer invalidNumber = stringToInteger.transform(invalidStr);
        System.out.println("Transformed invalid String to Integer: " + invalidNumber); // Вывод: Invalid input for Integer transformation: abc
        // Transformed invalid String to Integer: null

        // Преобразование Integer в String
        Integer intVal = 456;
        String intStr = integerToString.transform(intVal);
        System.out.println("Transformed Integer to String: " + intStr); // Вывод: Transformed Integer to String: Number: 456

        // Преобразование Double в String
        Double doubleVal = 78.91011;
        String doubleStr = doubleToString.transform(doubleVal);
        System.out.println("Transformed Double to String: " + doubleStr); // Вывод: Transformed Double to String: Formatted Double: 78.91
    }
}
```

## Расширение Generic интерфейса другим интерфейсом и его реализация

Расширение Generic интерфейса другим интерфейсом позволяет создавать более специализированные интерфейсы на основе
существующих. Это способствует более структурированной архитектуре и повышает гибкость системы. Далее мы рассмотрим
пример, где мы создаем обобщенный интерфейс ```Sortable```, который расширяет другой обобщенный
интерфейс ```ComparableEntity```. Затем реализуем этот расширенный интерфейс в классе ```Employee```.

```java
// Обобщенный интерфейс ComparableEntity с ограничением сверху
interface ComparableEntity<T extends Comparable<T>> {
    /**
     * Метод для сравнения текущего объекта с другим объектом типа T
     * @param other Объект для сравнения
     * @return Отрицательное число, ноль или положительное число в зависимости от сравнения
     */
    int compareTo(T other);
}
```

- **Параметр типа ```T```:** Ограничен сверху интерфейсом ```Comparable<T>```, что означает, что ```T``` должен
  реализовать ```Comparable<T>```.
- **Метод ```compareTo(T other)```:** Определяет контракт для сравнения текущего объекта с другим объектом типа ```T```.

```java
// Расширенный обобщенный интерфейс Sortable, наследующий ComparableEntity
interface Sortable<T extends Comparable<T>> extends ComparableEntity<T> {
    /**
     * Метод для сортировки списка объектов
     * @param list Список объектов для сортировки
     */
    void sort(List<T> list);
}
```

- **Наследование:** ```Sortable``` расширяет ```ComparableEntity<T>```, наследуя его методы и добавляя новые.
- **Метод** sort(List<T> list): Определяет контракт для сортировки списка объектов типа T.

```java

// Класс Employee, реализующий интерфейс Sortable
class Employee implements Sortable<Employee> {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры для имени и возраста
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Реализация метода compareTo из ComparableEntity
     * Сравнивает сотрудников по возрасту
     * @param other Другой сотрудник для сравнения
     * @return Отрицательное число, если текущий сотрудник младше,
     *         ноль, если равны по возрасту,
     *         положительное число, если старше
     */
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.age, other.age);
    }

    /**
     * Реализация метода sort из Sortable
     * Сортирует переданный список сотрудников по возрасту
     * @param list Список сотрудников для сортировки
     */
    @Override
    public void sort(List<Employee> list) {
        Collections.sort(list);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + '}';
    }
}
```

- **Метод** ```compareTo(Employee other)```: Реализует сравнение сотрудников по возрасту.
- **Метод** ```sort(List<Employee> list)```: Реализует сортировку списка сотрудников с использованием метода
  Collections.sort, который использует метод compareTo для определения порядка.

```java
// Класс для демонстрации использования интерфейсов ComparableEntity и Sortable
public class InterfaceInheritanceMain {
    public static void main(String[] args) {
        // Создаем список сотрудников
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30));
        employees.add(new Employee("Bob", 25));
        employees.add(new Employee("Charlie", 35));

        // Вывод списка до сортировки
        System.out.println("Before sorting:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Создаем экземпляр Employee для вызова метода sort
        Employee sorter = new Employee("Sorter", 40);
        sorter.sort(employees); // Сортировка списка по возрасту

        // Вывод списка после сортировки
        System.out.println("\nAfter sorting:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
```