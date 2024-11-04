# Лямбда функции и переменные

Чтобы присвоить лямбда-выражение переменной, эта переменная должна иметь тип **функционального интерфейса** — интерфейса с единственным абстрактным методом.

```java
@FunctionalInterface
public interface MyFunction {
    int apply(int a, int b);
}
```

Вы можете объявить переменную типа функционального интерфейса и присвоить ей лямбда-выражение, соответствующее сигнатуре абстрактного метода интерфейса.

```
MyFunction addition = (a, b) -> a + b;
```

- ```addition``` — переменная типа ```MyFunction```.
- ```(a, b) -> a + b``` — лямбда-выражение, которое принимает два параметра ```a``` и ```b``` и возвращает их сумму.

Вы можете вызвать метод функционального интерфейса через переменную, которая ссылается на лямбда-выражение.

```
int result = addition.apply(5, 3); // result будет равен 8
```

# Зачем?

## Передача поведения в методы

Присваивая лямбда-выражения переменным, вы можете передавать их в методы в качестве параметров, что позволяет создавать более гибкий и модульный код.

```
public static int compute(int a, int b, MyFunction operation) {
    return operation.apply(a, b);
}

// Использование
int sum = compute(5, 3, addition);
```

## Повторное использование кода

Вы можете определять различные реализации поведения и сохранять их в переменных для повторного использования в разных частях программы.

```
MyFunction subtraction = (a, b) -> a - b;
MyFunction multiplication = (a, b) -> a * b;

int diff = compute(10, 4, subtraction);       // 6
int product = compute(6, 7, multiplication);  // 42
```

## Упрощение работы с коллекциями и Stream API

Присваивание лямбда-выражений переменным позволяет создавать более читаемый код при работе с коллекциями.

```
List<String> names = Arrays.asList("Анна", "Борис", "Виктор");

Predicate<String> startsWithA = name -> name.startsWith("А");

List<String> filteredNames = names.stream()
    .filter(startsWithA)
    .collect(Collectors.toList());

System.out.println(filteredNames); // Вывод: [Анна]
```

## Обработка событий и колбэки

В GUI-приложениях или при работе с асинхронными операциями лямбда-выражения позволяют легко определять обработчики событий.

```
Button button = new Button("Нажми меня");
EventHandler<ActionEvent> onClick = event -> System.out.println("Кнопка нажата!");
button.setOnAction(onClick);
```

## Создание функций высшего порядка

Функции, принимающие или возвращающие другие функции, могут использовать переменные, ссылающиеся на лямбда-выражения.

```
public static MyFunction createMultiplier(int factor) {
    return (a, b) -> (a + b) * factor;
}

// Использование
MyFunction multiplier = createMultiplier(2);
int result = multiplier.apply(3, 4); // (3 + 4) * 2 = 14
```

## Композиция функций

Вы можете комбинировать функции, присвоенные переменным, для создания сложных операций.

```
Function<Integer, Integer> square = x -> x * x;
Function<Integer, Integer> increment = x -> x + 1;

Function<Integer, Integer> squareThenIncrement = square.andThen(increment);

int result = squareThenIncrement.apply(5); // (5 * 5) + 1 = 26
```




