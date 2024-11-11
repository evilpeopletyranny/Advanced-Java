# Аннотации

**Аннотации** — это специальные метаданные в Java, которые могут быть добавлены к классам, методам, полям, параметрам и
другим элементам программы. Они предоставляют дополнительную информацию для компилятора, инструментов разработки или во
время выполнения программы, не изменяя непосредственного поведения кода.

**Примеры встроенных аннотаций:**

- ```@Override```
- ```@Deprecated```
- ```@SuppressWarnings```

# Цель и использование аннотаций

## Цели

Аннотации используются для:

- **Предоставления информации компилятору**: обнаружение ошибок или подавление предупреждений.
- **Обработки во время компиляции**: генерация дополнительного кода или файлов.
- **Обработки во время выполнения**: изменение поведения программы на основе аннотаций (например, в фреймворках).

## Преимущества

- Улучшают читаемость и структуру кода.
- Облегчают интеграцию с фреймворками и библиотеками.
- Позволяют добавлять метаданные без изменения логики программы.

# Создание собственных аннотаций

Для создания собственной аннотации используется ключевое слово ```@interface```:

```java
public @interface MyAnnotation {
}
```

```java
public @interface Author {
    String name();

    String date();
}
```

# Мета-аннотации

**Мета-аннотации** — это специальные аннотации в Java, предназначенные для аннотирования других аннотаций. Они
используются для определения поведения и характеристик пользовательских аннотаций. Мета-аннотации позволяют
разработчикам контролировать, где и как могут применяться их аннотации, а также управлять их доступностью во время
выполнения или компиляции.

Основные мета-аннотации в Java:

- ```@Retention```
- ```@Target```
- ```@Documented```
- ```@Inherited```
- ```@Repeatable```

## @Retention

Аннотация ```@Retention``` определяет, как долго аннотация должна сохраняться. Она указывает, будет ли аннотация
доступна только в исходном коде, сохранена в скомпилированном .class файле или доступна во время выполнения через
рефлексию.

```@Retention``` принимает параметр типа ```RetentionPolicy```, который может принимать следующие значения:

- ```RetentionPolicy.SOURCE```: аннотация сохраняется только в исходном коде и отбрасывается компилятором. Она
  недоступна в скомпилированном байт-коде и во время выполнения.
- ```RetentionPolicy.CLASS```: аннотация сохраняется в скомпилированном .class файле, но недоступна во время выполнения
  через рефлексию. Это значение по умолчанию.
- ```RetentionPolicy.RUNTIME```: аннотация сохраняется в байт-коде и доступна во время выполнения через рефлексию. Это
  позволяет динамически получать информацию об аннотации в приложении.

### [Пример](code%2Fretention%2FMain.java)

Создание аннотации с мета-аннотацией ```@Retention```:

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}
```

Аннотация ```@MyAnnotation``` будет доступна во время выполнения, так как указано ```RetentionPolicy.RUNTIME```.

```java
public class TestClass {
    @MyAnnotation("Пример значения")
    public void myMethod() {
        // Реализация метода
    }
}
```

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Получение аннотации во время выполнения
        try {
            Method method = TestClass.class.getMethod("myMethod");
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("Значение аннотации: " + annotation.value());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## @Target

Аннотация ```@Target``` указывает, к каким элементам программы может быть применена аннотация. Это позволяет ограничить
использование аннотации только определенными конструкциями (например, только методами или только полями).

```@Target``` принимает массив параметров типа ```EleentType```. Возможные значения:

- ```ElementType.TYPE```: может быть применена к классу, интерфейсу, перечислению или аннотации.
- ```ElementType.FIELD```: к полю (полю объекта, статической переменной).
- ```ElementType.METHOD```: к методу.
- ```ElementType.PARAMETER```: к параметру метода или конструктора.
- ```ElementType.CONSTRUCTOR```: к конструктору.
- ```ElementType.LOCAL_VARIABLE```: к локальной переменной.
- ```ElementType.ANNOTATION_TYPE```: к аннотации (т.е. мета-аннотация).
- ```ElementType.PACKAGE```: к пакету.
- ```ElementType.TYPE_PARAMETER```: к параметру типа (с Java 8).
- ```ElementType.TYPE_USE```: к любому использованию типа (с Java 8).

### Пример

Создание аннотации с мета-аннотацией ```@Target```:

```java
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyAnnotation {
    String value();
}
```

Аннотация ```@MyAnnotation``` может быть применена только к полям и методам.

```java
public class ExampleClass {

    @MyAnnotation("Поле")
    private String myField;

    @MyAnnotation("Метод")
    public void myMethod() {
        // Реализация
    }

    // Неправильно: нельзя применять к классу
    // @MyAnnotation("Класс") // Это вызовет ошибку компиляции
    // public class InnerClass {
    //     // ...
    // }
}
```

## @Documented

Аннотация ```@Documented``` указывает, что аннотация должна быть включена в документацию JavaDoc. Если аннотация
помечена ```@Documented```, то при генерации документации с помощью инструмента javadoc информация об этой аннотации
будет включена в документацию для элементов, к которым она применена.

```java
import java.lang.annotation.Documented;

@Documented
public @interface MyAnnotation {
    String value();
}
```

## @Inherited

Аннотация ```@Inherited``` указывает, что если аннотация применена к классу, то она будет автоматически унаследована
подклассами этого класса. Однако, это работает только для классов и аннотаций с политикой RetentionPolicy.RUNTIME.

- Работает только с аннотациями, примененными к классам (т.е. ```@Target(ElementType.TYPE)```).
- Не наследуется методами, полями или другими элементами.
- Если подкласс явно аннотирован этой же аннотацией, то наследование не происходит (используется аннотация подкласса).

### [Пример](code%2Finherited%2FMain.java)

```java

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}
```

Аннотация ```@MyAnnotation``` будет унаследована подклассами класса, к которому она применена.

```java

@MyAnnotation("Базовый класс")
public class BaseClass {
    // Реализация
}

public class SubClass extends BaseClass {
    // Реализация
}
```

```java
public class Main {
    public static void main(String[] args) {
        Class<?> clazz = SubClass.class;
        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
        if (annotation != null) {
            System.out.println("Аннотация унаследована: " + annotation.value());
        } else {
            System.out.println("Аннотация не найдена");
        }
    }
}
```

## @Repeatable

Аннотация ```@Repeatable``` позволяет применять одну и ту же аннотацию к элементу несколько раз. Это введено в Java 8
для поддержки повторяющихся аннотаций.

### Как это работает?

- Создается контейнерная аннотация, которая содержит массив повторяющихся аннотаций.
- Мета-аннотация @Repeatable указывает на контейнерную аннотацию.

### [Пример](code%2Frepeatable%2FMain.java)

**Шаг 1: Создание повторяющейся аннотации**

```java
import java.lang.annotation.Repeatable;

@Repeatable(Schedules.class)
public @interface Schedule {
    String day();
}
```

---

**Шаг 2: Создание контейнерной аннотации**

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Schedules {
    Schedule[] value();
}
```

---

**Пример применения:**

```java
public class Worker {

    @Schedule(day = "Понедельник")
    @Schedule(day = "Среда")
    @Schedule(day = "Пятница")
    public void work() {
        // Реализация
    }
}
```

---

**Получение аннотаций во время выполнения:**

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Worker.class.getMethod("work");

        // Получение повторяющихся аннотаций напрямую
        Schedule[] schedules = method.getAnnotationsByType(Schedule.class);
        for (Schedule schedule : schedules) {
            System.out.println("День работы: " + schedule.day());
        }

        // Получение контейнерной аннотации
        Schedules schedulesContainer = method.getAnnotation(Schedules.class);
        if (schedulesContainer != null) {
            for (Schedule schedule : schedulesContainer.value()) {
                System.out.println("День работы (из контейнера): " + schedule.day());
            }
        }
    }
}
```

---

**Вывод**

```
День работы: Понедельник
День работы: Среда
День работы: Пятница
День работы (из контейнера): Понедельник
День работы (из контейнера): Среда
День работы (из контейнера): Пятница
```

# Заключение

Мета-аннотации в Java предоставляют мощные инструменты для определения поведения пользовательских аннотаций. Понимание
их назначения и правильного использования позволяет создавать гибкие и хорошо структурированные приложения, особенно при
работе с фреймворками и библиотеками, которые широко используют аннотации.
