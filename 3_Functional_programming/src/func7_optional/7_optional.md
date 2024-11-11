# Optional

```Optional``` — это специальный класс в Java, введенный в версии Java 8, который представляет собой контейнер для
значения, которое может быть ```null``` или не быть ```null```. Он предоставляет удобные методы для работы с возможным
отсутствием значения, позволяя писать более безопасный и чистый код, минимизируя
возникновение ```NullPointerException```.

# Зачем?

## Проблема с null

В Java значение ```null``` часто используется для обозначения отсутствия объекта. Однако использование null может
приводить к ряду проблем

- ```NullPointerException``` **(NPE)**: Одно из самых распространенных исключений, возникающее при попытке вызвать метод
  на ```null``` ссылке.
- **Сложность понимания кода:** Не всегда очевидно, может ли метод вернуть ```null```, что затрудняет чтение и поддержку
  кода.
- **Повышенный риск ошибок:** Программисты могут забыть проверить значения на ```null```, что приводит к потенциальным
  багам.

## Решение через Optional

```Optional``` решает эти проблемы, предоставляя явный способ работы с возможным отсутствием значения:

- **Явное указание на возможность отсутствия значения:** Если метод возвращает ```Optional```, это означает, что
  результат может быть пустым.
- **Уменьшение вероятности NullPointerException:** Принуждает разработчика обрабатывать отсутствие значения.
- **Улучшение читаемости кода:** Код становится более понятным и самодокументируемым.

# Идея

Концепция ```Optional``` взята из функциональных языков программирования:

- **Haskell**: Использует тип ```Maybe```, который может быть ```Just value``` или ```Nothing```.

Схожие с ```Optional``` решения из других языков:

- **Scala**: Имеет тип ```Option[T]``` с подтипами ```Some(value)``` и ```None```.
- **Kotlin**: Использует систему типов, где ```null``` является частью типа, и операторы безопасного вызова.
- **C#**: Предоставляет ```Nullable<T>``` для значимых типов.

Идея заключается в том, чтобы сделать отсутствие значения частью типа, заставляя разработчика явно обрабатывать этот
случай.

# Использование ```Optional```

## Создание ```Optional```

---

- Пустой ```Optional```:

```
Optional<String> emptyOpt = Optional.empty();
```

---

- ```Optional``` с **не-null** значением:

```
Optional<String> opt = Optional.of("Hello");
```

Важно: Если передать ```null``` в ```Optional.of()```, будет выброшено ```NullPointerException```.

## Проверка наличия значения

---

- ```isPresent()```: Проверяет, присутствует ли значение.

```
if (opt.isPresent()) {
    System.out.println(opt.get());
}
```

---

- ```ifPresent(Consumer)```: Выполняет действие, если значение присутствует.

```
opt.ifPresent(System.out::println);
```

---

## Получение значения

---

- ```get()```: Возвращает значение, если оно присутствует, иначе выбрасывает ```NoSuchElementException```

```
String value = opt.get();
```

---

- ```orElse(T other)```: Возвращает значение, если оно присутствует, иначе возвращает ```other```.

```
String value = opt.orElse("Default");
```

---

- ```orElseGet(Supplier<? extends T> supplier)```: Возвращает значение, если оно присутствует, иначе
  вызывает ```supplier``` и возвращает его результат.

```
String value = opt.orElseGet(() -> "Generated Value");
```

---

- ```orElseThrow(Supplier<? extends X> exceptionSupplier)```: Возвращает значение, если оно присутствует, иначе
  выбрасывает исключение, предоставленное ```exceptionSupplier```.

```
String value = opt.orElseThrow(() -> new IllegalArgumentException("Value not present"));
```

---

## Преобразование и фильтрация

---

- ```map(Function<? super T, ? extends U> mapper)```: Преобразует значение, если оно присутствует.

```
Optional<Integer> lengthOpt = opt.map(String::length);
```

---

- ```flatMap(Function<? super T, Optional<U>> mapper)```: Преобразует значение и возвращает ```Optional```.

```
Optional<String> upperOpt = opt.flatMap(s -> Optional.of(s.toUpperCase()));
```

---

- ```filter(Predicate<? super T> predicate)```: Возвращает ```Optional``` с значением, если оно соответствует предикату.

```
Optional<String> filteredOpt = opt.filter(s -> s.startsWith("H"));
```

---

# Примеры использования

## Пример с проверкой значения

```java
public class Main {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable(getUserName());

        // Проверка наличия значения
        if (opt.isPresent()) {
            System.out.println("User name: " + opt.get());
        } else {
            System.out.println("User name not found");
        }

        // Альтернативный способ
        opt.ifPresentOrElse(
                name -> System.out.println("User name: " + name),
                () -> System.out.println("User name not found")
        );
    }

    // "Функция, которая может вернуть значение или null"
    public static String getUserName() {
        return null;
    }
}
```

## Пример с ```orElse``` и ```orElseGet```

```java
public class Main {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable(getConfigValue());

        String value = opt.orElse("Default Value");
        System.out.println("Config Value: " + value);

        String valueFromSupplier = opt.orElseGet(() -> {
            // Дополнительная логика
            return "Generated Default Value";
        });
        System.out.println("Config Value: " + valueFromSupplier);
    }

    // "Функция, которая может вернуть значение или null"
    public static String getConfigValue() {
        return null;
    }
}
```

## Пример с ```map``` и цепочкой вызовов

```java
public class User {
    private String name;
    private Address address;

    // Геттеры и сеттеры
}

public class Address {
    private String city;

    // Геттеры и сеттеры
}

public class Main {
    public static void main(String[] args) {
        User user = getUser();

        String city = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse("Unknown City");

        System.out.println("City: " + city);
    }

    public static User getUser() {
        return null; // Или вернуть пользователя
    }
}
```

# Сравнение подхода с ```null``` и с ```Optional```

## ```null```

```
public String getUserEmail(User user) {
    if (user != null) {
        Email email = user.getEmail();
        if (email != null) {
            return email.getAddress();
        }
    }
    return "Email not available";
}
```

## ```Optional```

```
public String getUserEmail(User user) {
    return Optional.ofNullable(user)
            .map(User::getEmail)
            .map(Email::getAddress)
            .orElse("Email not available");
}
```

# Советы по использованию Optional

## Не использовать ```Optional``` в полях классов

```Optional``` предназначен для использования в возвращаемых значениях методов, а не для полей классов.
Использование ```Optional``` в полях может привести к сложности в сериализации и дополнительным накладным расходам.

## Не использовать ```Optional``` в аргументах методов

Передача ```Optional``` в качестве параметра метода считается плохой практикой. Лучше принять конкретный тип и
обрабатывать возможный ```null``` внутри метода.

## Использовать методы ```orElseGet``` вместо ```orElse```, если вычисление значения ресурсоемкое

Метод ```orElse``` вычисляет значение, даже если оно не будет использовано, тогда как ```orElseGet``` вычисляет значение
лениво.

```
String value = opt.orElse(createDefault()); // createDefault() будет вызван всегда
String value = opt.orElseGet(() -> createDefault()); // createDefault() вызовется только если opt пустой
```

# Плюсы

- Явная индикация возможности отсутствия значения
- Снижение вероятности возникновения ```NullPointerException```
- Улучшение читаемости и поддерживаемости кода
- Поддержка функционального стиля программирования

# Ограничения и рекомендации

- **Не злоупотребляйте ```Optional```**: не стоит оборачивать все значения в ```Optional``` без необходимости.
- **Производительность**: создание дополнительных объектов может влиять на производительность в критических участках
  кода.
- **Не используйте ```get()``` без проверки**: метод ```get()``` должен использоваться только если вы уверены, что
  значение присутствует.

# Заключение

```Optional``` является полезным инструментом для обработки значений, которые могут отсутствовать. Он повышает
надежность и читаемость кода, заставляя разработчиков явно обрабатывать случаи, когда значение может быть ```null```.
При правильном использовании ```Optional``` помогает избежать многих распространенных ошибок и сделать код более
выразительным.