# Паттерн "Proxy" при помощи рефлексии

**Прокси** (от англ. _proxy_ — «представитель», «уполномоченный») — это объект, который выступает в роли суррогата или
заместителя другого объекта. В Java прокси позволяет перехватывать вызовы методов и выполнять дополнительную логику до
или после вызова реального метода.

**Рефлексия** предоставляет механизм для создания динамических прокси-объектов во время выполнения программы. Это
особенно полезно для реализации таких паттернов, как:

- **Логирование**: автоматическое логирование вызовов методов.
- **Транзакции**: управление транзакциями в базах данных.
- **Безопасность**: проверка прав доступа.
- **Кэширование**: кэширование результатов вызовов методов.
- **Аспектно-ориентированное программирование (AOP)**: отделение сквозной функциональности от бизнес-логики.

# Динамическое прокси в Java

Динамический прокси — это объект, который реализует заданный интерфейс (или интерфейсы) и перенаправляет вызовы его
методов на специальный обработчик, который может выполнять дополнительную логику. В Java динамические прокси создаются с
использованием класса ```java.lang.reflect.Proxy``` и интерфейса ```java.lang.reflect.InvocationHandler```.

- **Гибкость**: можно динамически изменять поведение объектов во время выполнения.
- **Модульность**: отделение сквозной функциональности от основной логики.
- **Повторное использование кода**: общая логика может быть реализована в одном месте и применена к разным объектам.

# Компоненты реализация

## Интерфейс

Прокси в Java могут реализовывать только интерфейсы. Поэтому целевой класс должен реализовывать интерфейс, методы которого будут перехватываться.

## Реальный объект (реализация интерфейса)

Это объект, вызовы методов которого мы хотим перехватывать и, возможно, дополнять дополнительной логикой.

## Интерфейс ```InvocationHandler```

Это интерфейс, который содержит метод invoke. Этот метод вызывается каждый раз, когда на прокси-объекте вызывается любой метод из реализованных им интерфейсов.

```java
public interface InvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

## Класс ```Proxy```

Этот класс содержит статический метод ```newProxyInstance```, который используется для создания прокси-объектов.

# Алгоритм создания динамического прокси

1. **Определить интерфейс**, методы которого будут перехватываться.
2. **Создать реализацию интерфейса** (реальный объект), который будет выполнять основную бизнес-логику.
3. **Реализовать интерфейс** ```InvocationHandler```, который будет перехватывать вызовы методов и выполнять дополнительную логику.
4. **Создать прокси-объект** с помощью метода ```Proxy.newProxyInstance```, указав:
   - Класс загрузчика (```ClassLoader```).
   - Массив интерфейсов, которые должен реализовывать прокси.
   - Экземпляр ```InvocationHandler```.
5. **Использовать прокси-объект** вместо реального объекта.

# Пример реализации

## Шаг 1: Определение интерфейса

Создадим интерфейс ```Calculator``` с несколькими методами для выполнения арифметических операций.

```java
public interface Calculator {
    int add(int a, int b);
    int subtract(int a, int b);
    int multiply(int a, int b);
    double divide(int a, int b);
}
```

## Шаг 2: Создание реализации интерфейса

Создадим класс ```CalculatorImpl```, который реализует интерфейс ```Calculator```.

```java
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Деление на ноль");
        return (double) a / b;
    }
}
```

## Шаг 3: Реализация ```InvocationHandler```

Создадим класс ```LoggingInvocationHandler```, который будет реализовывать интерфейс ```InvocationHandler```. Он будет перехватывать вызовы методов и добавлять логирование.

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target; // Реальный объект, вызовы к которому будут перехватываться

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Логирование перед вызовом метода
        System.out.println("Вызов метода: " + method.getName() + ", с аргументами: " + Arrays.toString(args));

        Object result;
        try {
            // Вызов реального метода на целевом объекте
            result = method.invoke(target, args);

            // Логирование после успешного выполнения метода
            System.out.println("Метод " + method.getName() + " выполнен успешно, результат: " + result);
        } catch (Throwable t) {
            // Логирование в случае исключения
            System.out.println("Метод " + method.getName() + " выбросил исключение: " + t.getCause());
            throw t;
        }

        return result;
    }
}
```

- **Конструктор**: принимает реальный объект, вызовы к которому будут перехватываться.
- **Метод** ```invoke```:
  - Логирует имя метода и аргументы перед вызовом.
  - Вызывает реальный метод с помощью ```method.invoke(target, args)```.
  - Логирует результат после выполнения.
  - Обрабатывает исключения и логирует их.

## Шаг 4: Создание прокси-объекта

Создадим класс ```ProxyFactory```, который будет создавать прокси-объекты.

```java
import java.lang.reflect.Proxy;

public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceType, T implementation) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new LoggingInvocationHandler(implementation)
        );
    }
}
```

- **Метод** ```createProxy```:
  - Принимает тип интерфейса и реализацию.
  - Использует ```Proxy.newProxyInstance``` для создания прокси.
  - Возвращает прокси-объект, приведенный к типу интерфейса.
- **Метод** ```Proxy.newProxyInstance```:
  - ```interfaceType.getClassLoader()```: загрузчик классов.
  - ```new Class<?>[]{interfaceType}```: массив интерфейсов, которые должен реализовывать прокси.
  - ```new LoggingInvocationHandler(implementation)```: экземпляр ```InvocationHandler```, который будет обрабатывать вызовы методов.

## Шаг 5: Использование прокси-объекта

```java
public class Main {
    public static void main(String[] args) {
        // Создаем реальный объект
        Calculator calculator = new CalculatorImpl();

        // Создаем прокси для реального объекта
        Calculator calculatorProxy = ProxyFactory.createProxy(Calculator.class, calculator);

        try {
            // Вызываем методы через прокси
            int sum = calculatorProxy.add(5, 3);
            int diff = calculatorProxy.subtract(10, 4);
            int product = calculatorProxy.multiply(6, 7);
            double quotient = calculatorProxy.divide(20, 4);
            double error = calculatorProxy.divide(10, 0); // Будет выброшено исключение
        } catch (Exception e) {
            System.out.println("Произошло исключение: " + e.getMessage());
        }
    }
}
```

- Создаем экземпляр ```CalculatorImpl```
- Создаем прокси-объект ```calculatorProxy```
- Вызываем методы через прокси.
- Обрабатываем возможные исключения.

# Детальное объяснение работы прокси

## Как прокси перехватывает вызовы методов

- Когда вы вызываете метод на прокси-объекте, Java перенаправляет этот вызов в метод ```invoke``` вашего ```InvocationHandler```.
- В методе ```invoke``` у вас есть возможность выполнить любую дополнительную логику.
- Вы можете вызвать реальный метод на целевом объекте, используя ```method.invoke(target, args)```.
- Вы можете модифицировать аргументы или результат метода.

## Параметры метода ```invoke```

- ```Object proxy```: прокси-объект, на котором был вызван метод (обычно не используется).
- ```Method method```: объект Method, представляющий вызываемый метод.
- ```Object[] args```: массив аргументов, переданных в метод.

## Вызов реального метода

- Используется ```method.invoke(target, args)``` для вызова реального метода на целевом объекте.
- При вызове могут быть выброшены исключения, которые нужно обработать.

## Обработка исключений

- ```InvocationTargetException```: оборачивает исключение, выброшенное вызываемым методом.
- Нужно вызвать ```getCause()``` для получения исходного исключения.
