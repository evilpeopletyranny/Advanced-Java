# Лямбда функции

Лямбда-функции, также известные как лямбда-выражения или анонимные функции, — это конструкция в языках программирования,
позволяющая создавать функции без указания их имени. Они используются для передачи поведения в методы, что позволяет
писать код в функциональном стиле. В Java лямбда-выражения были введены в версии Java 8 и стали одним из наиболее
значимых обновлений языка.

## История

Концепция лямбда-функций берет свое начало из лямбда-исчисления — формальной системы математической логики и
теоретической информатики, разработанной Алонзо Чёрчем в 1930-х годах. Лямбда-исчисление предоставляет способ
определения функций, применения функций к аргументам и использования функций как значений. Оно является теоретической
основой функционального программирования.

# Лямбда функции в Java

## Функциональный интерфейсы

Лямбда-выражения в Java реализованы через **функциональные интерфейсы** — интерфейсы с единственным абстрактным методом (SAM — Single Abstract Method). Лямбда-выражение предоставляет реализацию этого абстрактного метода.

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int x, int y);
}
```

```
Calculator add = (a, b) -> a + b;
int result = add.calculate(5, 3); // result = 8
```

## Синтаксис лямбда-выражений

Общий синтаксис лямбда-выражения в Java:

```
(параметры) -> { тело }
```

- **Параметры**: Список входных параметров функции. Если параметр один и тип может быть выведен, скобки можно опустить.
- **Стрелка (```->```)**: Разделитель между параметрами и телом функции.
- **Тело**: Код, который будет выполнен при вызове функции. Если тело состоит из одного выражения, фигурные скобки можно опустить.

### Примеры 

1. Без параметров:

```
() -> System.out.println("Привет, мир!");
```

2. С одним параметром:

```
x -> x * x
```

3. С несколькими параметрами:

```
(x, y) -> x + y
```

4. С указанием типов параметров:

```
(int x, int y) -> { return x - y; }
```

## Особенности и ограничения лямбда-выражений в Java

### Захват переменных из внешней области видимости

Лямбда-выражения могут использовать переменные из внешней области видимости, но эти переменные должны быть **эффективно финальными** (их значение не изменяется после инициализации).

```
String prefix = "Привет, ";
Consumer<String> greeter = name -> System.out.println(prefix + name);

greeter.accept("Мир!"); // Вывод: Привет, Мир!
```

### Исключения в лямбда-выражениях

Если функциональный интерфейс не объявляет проверяемых исключений, то лямбда-выражение не может выбрасывать такие исключения без обработки.

**Решения**:

- Обернуть код в лямбда-выражении в try-catch.
- Использовать свой функциональный интерфейс, объявляющий исключение.

### Ограничения на использование ```this```

Внутри лямбда-выражения ```this``` ссылается на экземпляр внешнего класса, а не на лямбда-выражение.

## Базовые функиональные интерфейсы

### 1. java.lang.Runnable
- **Описание**: Представляет задачу, не принимающую входных параметров и не возвращающую результат.
- **Абстрактный метод**: ```void run()```

```
Runnable task = () -> System.out.println("Задача выполнена");
task.run();
```

### 2. java.util.concurrent.Callable<V>
- **Описание**: Представляет задачу, не принимающую входных параметров, но возвращающую результат типа V. Может выбрасывать исключение.
- **Абстрактный метод**: ```V call() throws Exception```

```
Callable<String> task = () -> "Результат";
String result = task.call();
```

### 3. java.util.function.Predicate<T>
- **Описание**: Принимает объект типа ```T``` и возвращает ```boolean```. Используется для проверки условия.
- **Абстрактный метод**: ```boolean test(T t)```

```
Predicate<Integer> isEven = n -> n % 2 == 0;
boolean result = isEven.test(4); // true
```

### 4. java.util.function.Consumer<T>
- **Описание**: Принимает объект типа ```T```, выполняет над ним операцию, ничего не возвращает.
- **Абстрактный метод**: ```void accept(T t)```

```
Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello, World!");
```

### 5. java.util.function.Function<T, R>
- **Описание**: Принимает объект типа ```T``` и возвращает объект типа ```R```. Используется для преобразования данных.
- **Абстрактный метод**: ```R apply(T t)```

```
Function<String, Integer> stringLength = s -> s.length();
int length = stringLength.apply("Hello"); // 5
```

### 6. java.util.function.Supplier<T>
- **Описание**: Не принимает входных параметров, но возвращает объект типа ```T```. Используется для поставки значений.
- **Абстрактный метод**: ```T get()```

```
Supplier<Double> randomSupplier = () -> Math.random();
double randomValue = randomSupplier.get();
```

### 7. java.util.function.UnaryOperator<T>
- **Описание**: Специализация ```Function<T, R>``` для случая, когда входной и выходной типы совпадают ```(Function<T, T>)```. Применяет унарную операцию к объекту типа ```T```.
- **Абстрактный метод**: ```T apply(T t)```

```
UnaryOperator<Integer> square = n -> n * n;
int result = square.apply(5); // 25
```

### 8. java.util.function.BinaryOperator<T>
- **Описание**: Специализация ```BiFunction<T, U, R>``` для случая, когда все три типа совпадают (```BiFunction<T, T, T>```). Применяет бинарную операцию к двум объектам типа ```T``` и возвращает результат типа ```T```.
- **Абстрактный метод**: ```T apply(T t1, T t2)```

```
BinaryOperator<Integer> sum = (a, b) -> a + b;
int result = sum.apply(3, 4); // 7
```

### 13. java.util.Comparator<T>
- **Описание**: Используется для сравнения двух объектов типа ```T```. Хотя в интерфейсе более одного абстрактного метода из-за методов по умолчанию, он является функциональным интерфейсом, поскольку все методы, кроме compare, имеют реализацию по умолчанию.
- **Абстрактный метод**: ```int compare(T o1, T o2)```

```
Comparator<String> lengthComparator = (a, b) -> Integer.compare(a.length(), b.length());
List<String> names = Arrays.asList("Анна", "Борислав", "Виктория");
names.sort(lengthComparator);
```

### 14. java.lang.Comparable<T>
- **Описание**: Определяет естественный порядок объектов. Имеет один абстрактный метод compareTo, поэтому может рассматриваться как функциональный интерфейс.
- **Абстрактный метод**: ```int compareTo(T o)```

### И тд.

Есть ещё множество функциональных интерфейсов как и общего назначения, так и специализированных...

## Создание собсвтенного функционального интерфейса

Чтобы создать собственный функциональный интерфейс, необходимо:

1. **Определить интерфейс с одним абстрактным методом.**
2. **(Необязательно) Добавить аннотацию @FunctionalInterface.**

Аннотация ```@FunctionalInterface``` не обязательна, но рекомендуется, так как позволяет компилятору проверять, что интерфейс соответствует требованиям функционального интерфейса (т.е. имеет только один абстрактный метод).

### Пример создания и использования собственного функционального интерфейса 

**Определение функционального интерфейса**

Допустим, мы хотим создать функциональный интерфейс, который принимает два целых числа и возвращает результат их обработки.

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}
```

**Использование функционального интерфейса с лямбда-выражениями**

Теперь мы можем использовать наш интерфейс ```Calculator``` с лямбда-выражениями для реализации различных операций.

```java
public class Main {
    public static void main(String[] args) {
        // Реализация сложения
        Calculator addition = (a, b) -> a + b;
        int sum = addition.calculate(5, 3);
        System.out.println("Сумма: " + sum); // Вывод: Сумма: 8

        // Реализация вычитания
        Calculator subtraction = (a, b) -> a - b;
        int difference = subtraction.calculate(5, 3);
        System.out.println("Разность: " + difference); // Вывод: Разность: 2

        // Реализация умножения
        Calculator multiplication = (a, b) -> a * b;
        int product = multiplication.calculate(5, 3);
        System.out.println("Произведение: " + product); // Вывод: Произведение: 15

        // Реализация деления
        Calculator division = (a, b) -> a / b;
        int quotient = division.calculate(10, 2);
        System.out.println("Частное: " + quotient); // Вывод: Частное: 5
    }
}
```

### Использование с методами высшего порядка

Вы можете передавать функциональные интерфейсы в методы для реализации функций высшего порядка.

```java
public class CalculatorDemo {
    public static int compute(int a, int b, Calculator operation) {
        return operation.calculate(a, b);
    }

    public static void main(String[] args) {
        int result = compute(10, 5, (x, y) -> x + y);
        System.out.println("Результат: " + result); // Вывод: Результат: 15
    }
}
```

### Использование с методами высшего порядка

Вы можете добавлять методы по умолчанию и статические методы в функциональный интерфейс, не нарушая его функциональности, поскольку они не считаются абстрактными.

_Это странно и этим не пользуются, но так можно_

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);

    // Метод по умолчанию
    default void description() {
        System.out.println("Это калькулятор.");
    }

    // Статический метод
    static void info() {
        System.out.println("Функциональный интерфейс Calculator");
    }
}
```

### Функциональный интерфейс с Дженериком

Вы можете создать функциональный интерфейс с обобщением (дженериком), чтобы сделать его более универсальным.

```java
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
```

```java
public class Main {
    public static void main(String[] args) {
        Converter<String, Integer> stringToInteger = s -> Integer.parseInt(s);
        int number = stringToInteger.convert("42");
        System.out.println("Число: " + number); // Вывод: Число: 42
    }
}
```
