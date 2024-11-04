`# Функции высшего порядка

**Функции высшего порядка** — это функции, которые принимают другие функции в качестве аргументов или возвращают функции в качестве результата. Они являются фундаментальной концепцией в функциональном программировании и позволяют создавать более абстрактный и переиспользуемый код.

В Java, начиная с версии Java 8, появилась поддержка функционального программирования через лямбда-выражения и функциональные интерфейсы, что позволяет реализовывать функции высшего порядка.

## Зачем?

Функции высшего порядка позволяют:

- **Повышать абстракцию кода**: отделять общую логику от конкретных реализаций.
- **Повторно использовать код**: создавать общие методы, которые могут работать с разными действиями.
- **Упрощать работу с коллекциями**: применять операции фильтрации, преобразования и агрегации.
- **Создавать более модульный и гибкий код**: легко расширять и модифицировать функциональность без изменения основной логики.

# Функции высшего порядка в Java

##  Функции высшего порядка, принимающие функции в качестве аргументов

Вы можете создать метод, который принимает функциональный интерфейс в качестве параметра. Это позволит передавать в метод разные реализации поведения.

```
public static <T> void process(T value, Consumer<T> consumer) {
    consumer.accept(value);
}
```

Здесь ```process``` — функция высшего порядка, принимающая объект типа ```T``` и функцию ```Consumer<T>```.

### Пример

```java
@FunctionalInterface
public interface Operation {
    int apply(int a, int b);
}

public class Calculator {
    public static int compute(int a, int b, Operation operation) {
        return operation.apply(a, b);
    }
}

public class Main {
    public static void main(String[] args) {
        Operation addition = (x, y) -> x + y;
        Operation multiplication = (x, y) -> x * y;

        int sum = Calculator.compute(5, 3, addition); // 8
        int product = Calculator.compute(5, 3, multiplication); // 15

        System.out.println("Сумма: " + sum);
        System.out.println("Произведение: " + product);
    }
}
```

- ```Operation``` — функциональный интерфейс с методом apply.
- ```compute``` — функция высшего порядка, принимающая Operation в качестве параметра.
- Мы передаем разные реализации ```Operation``` в ```compute```, позволяя ему выполнять различные операции.



## Функции высшего порядка, возвращающие функции

Вы можете создать метод, который возвращает функциональный интерфейс. Это позволяет создавать функции-генераторы, которые создают другие функции на лету.


```
public static Function<Integer, Integer> createMultiplier(int factor) {
    return n -> n * factor;
}
```

Здесь ```createMultiplier``` — функция высшего порядка, возвращающая функцию, умножающую число на заданный множитель.

### Пример

```java
public class OperatorFactory {
    public static Operation createOperator(String operator) {
        switch (operator) {
            case "+":
                return (a, b) -> a + b;
            case "-":
                return (a, b) -> a - b;
            case "*":
                return (a, b) -> a * b;
            case "/":
                return (a, b) -> a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Operation addition = OperatorFactory.createOperator("+");
        int result = addition.apply(10, 5); // 15
        System.out.println("Результат: " + result);
    }
}
```

- ```createOperator``` — функция высшего порядка, возвращающая реализацию Operation в зависимости от переданного оператора.
- Мы можем динамически получать нужную функцию для выполнения операции.

# Больше веселья с функциями высшего порядка

## Композиция функций

Вы можете создавать функции, которые принимают функции и возвращают новые функции.

**Функция, композирующая две функции**

```
public static <T, R, V> Function<T, V> compose(Function<T, R> f, Function<R, V> g) {
    return x -> g.apply(f.apply(x));
}

Function<Integer, Integer> doubleValue = x -> x * 2;
Function<Integer, Integer> square = x -> x * x;

Function<Integer, Integer> doubleThenSquare = compose(doubleValue, square);

int result = doubleThenSquare.apply(3); // (3 * 2) ^ 2 = 36
System.out.println("Результат: " + result);
```

- ```compose``` — функция высшего порядка, принимающая две функции и возвращающая их композицию.
- Мы создаем новую функцию ```doubleThenSquare```, которая сначала удваивает значение, а затем возводит в квадрат.

## Каррирование

**Каррирование** — это преобразование функции, принимающей несколько аргументов, в цепочку функций, каждая из которых принимает один аргумент.

**_В Java каррирование функций выглядит действиельно плохо и почти не встречается. К тому же есть специализированные библиотеки где это реализовано лучше чем при базовом функционале._**

```
public static Function<Integer, Function<Integer, Integer>> curryAddition() {
    return a -> b -> a + b;
}

Function<Integer, Function<Integer, Integer>> curriedAdd = curryAddition();
Function<Integer, Integer> addFive = curriedAdd.apply(5);

int result = addFive.apply(3); // 8
System.out.println("Результат: " + result);
```

- ```curryAddition``` — функция высшего порядка, возвращающая функцию, которая в свою очередь возвращает функцию.
- Мы сначала фиксируем первое значение ```a```, затем применяем второе значение ```b```.
