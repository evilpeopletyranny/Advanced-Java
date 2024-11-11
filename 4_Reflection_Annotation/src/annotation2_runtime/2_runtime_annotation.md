# Работа с RUNTIME аннотациями

**Runtime аннотации** — это аннотации, доступные во время выполнения приложения, благодаря чему их можно использовать
для динамического изменения поведения программы с помощью рефлексии.

Чтобы аннотация была доступна во время выполнения, она должна быть помечена мета-аннотацией ```@Retention(
RetentionPolicy.RUNTIME)```. Это позволяет получать информацию об аннотации через рефлексию.

Во время выполнения можно анализировать аннотации и в зависимости от их наличия и значений выполнять определенные
действия. Это позволяет создавать более гибкие и адаптивные приложения.

# Пример реализации Dependency Injection с использованием runtime аннотаций

**Dependency Injection (DI)** — это паттерн проектирования, который позволяет отделить создание зависимостей от их
использования, повышая модульность и тестируемость кода. В Java DI часто реализуется с помощью фреймворков (например,
Spring), которые используют аннотации для автоматического внедрения зависимостей.

## Постановка задачи

Реализовать простой DI-контейнер, который будет автоматически внедрять зависимости в поля классов, помеченные
аннотацией ```@Inject```. Контейнер должен:

- Создавать экземпляры классов.
- Находить поля, помеченные ```@Inject```.
- Внедрять соответствующие зависимости в эти поля.

## [Реализация](code%2FMain.java)

**Важно!** В примере используется упрощенный пример **DI** через жесткую привязку по типу класса. Для реализации DI
через интерфейс необходимо иметь уточнение какой именно класс реализующий интерфейс должен быть использован, что
ослажнит код.

---

Аннотация ```@Inject```

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {
}
```

---

Класс, который будет внедряться

```java
public class Service {
    public void execute() {
        System.out.println("Выполнение ServiceImpl.");
    }
}
```

---

Класс, в который происходит внедрение

```java
public class Client {
    @Inject
    private Service service;

    public void doWork() {
        service.execute();
    }
}
```

---

Класс контейнера - обрабатывает и внедряет зависимости

```java
import java.lang.reflect.Field;

public class DIContainer {

    public <T> T getInstance(Class<T> clazz) throws Exception {
        return createInstance(clazz);
    }

    private <T> T createInstance(Class<T> clazz) throws Exception {
        // Создаем новый экземпляр класса
        T instance = clazz.getDeclaredConstructor().newInstance();

        // Инъекция зависимостей в поля, помеченные @Inject
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                // Получаем тип поля
                Class<?> fieldType = field.getType();

                // Рекурсивно создаем экземпляр зависимости
                Object dependency = createInstance(fieldType);

                // Устанавливаем значение поля
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }
        return instance;
    }
}
```

---

```java
public class Main {
    public static void main(String[] args) {
        try {
            DIContainer container = new DIContainer();
            Client client = container.getInstance(Client.class);
            client.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

# Остальные типы аннотации

Работа с аннотациями которые не Runtime ещё более сложна и требует подключение сторонних библиотек/реалиазцию
обработчиков аннотаций и настройки окружения.