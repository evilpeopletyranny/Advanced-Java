# Динамическое создание объектов

Рефлексия в Java предоставляет возможность динамически создавать объекты классов во время выполнения, даже если классы
неизвестны на этапе компиляции. Это особенно полезно в ситуациях, когда требуется гибкость, например, при разработке
плагинов, модульных систем, фреймворков и библиотек.

## Устаревший подход: ```Class.newInstance()```

**Важно**: Начиная с Java 9, метод ```Class.newInstance()``` считается устаревшим, так как он не обрабатывает исключения
конструктора и требует наличия публичного конструктора без параметров. Рекомендуется использовать
Constructor.newInstance()

```
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Object instance = clazz.newInstance(); // Устаревший метод
} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

## Использование ```Constructor.newInstance()```

Этот метод предоставляет больше гибкости и безопасности. Он позволяет:

- Выбрать конкретный конструктор, включая приватные и защищенные.
- Передать параметры в конструктор.
- Обрабатывать исключения, выбрасываемые конструктором.

```
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    
    // Получение конструктора без параметров
    Constructor<?> constructor = clazz.getConstructor();
    Object instance = constructor.newInstance();
    
    // Или получение конструктора с параметрами
    Constructor<?> paramConstructor = clazz.getConstructor(String.class, int.class);
    Object paramInstance = paramConstructor.newInstance("Example", 42);
} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
         IllegalAccessException | InvocationTargetException e) {
    e.printStackTrace();
}
```

## Создание экземпляров при помощи фабричных методов

Иногда классы предоставляют статические фабричные методы для создания экземпляров. С помощью рефлексии можно вызвать эти
методы.

```
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Method factoryMethod = clazz.getMethod("createInstance", String.class);
    Object instance = factoryMethod.invoke(null, "Parameter");
} catch (Exception e) {
    e.printStackTrace();
}
```

Метод ```Method.invoke()``` первым параметром принимает объект от которого будет вызван метод. Для статических методов
передаётся ```null```.

# Примеры

## Динамическая загрузка плагинов

Представим, что у нас есть приложение, которое поддерживает плагинную архитектуру. Плагины реализуют определенный интерфейс и находятся в отдельном пакете или даже загружаются из внешних JAR-файлов.

---

**Интерфейс плагина**

```java
public interface Plugin {
    void execute();
}
```

---

**Загрузка и создание экземпляра плагина**

```java
public class PluginLoader {
    public static void main(String[] args) {
        String pluginClassName = "com.plugins.MyPlugin";
        try {
            // Загрузка класса плагина
            Class<?> pluginClass = Class.forName(pluginClassName);
            
            // Проверка, реализует ли класс интерфейс Plugin
            if (Plugin.class.isAssignableFrom(pluginClass)) {
                // Приведение к типу Plugin
                Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
                
                // Вызов метода плагина
                plugin.execute();
            } else {
                System.out.println("Класс не реализует интерфейс Plugin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

- Возможность добавлять новые плагины без изменения основного кода.
- Гибкость и расширяемость приложения.

## Фреймворки, такие как Spring, используют рефлексию для создания объектов и внедрения зависимостей.

Фреймворки, такие как Spring, используют рефлексию для создания объектов и внедрения зависимостей.

---

**Аннотация ```@Autowired```**

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
```

---

**Классы с зависимостями**

```java
public class ServiceA {
    public void doSomething() {
        System.out.println("ServiceA выполняет работу.");
    }
}

public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void performAction() {
        serviceA.doSomething();
    }
}
```

---

**Контейнер для внедрения зависимостей**

```java
public class DIContainer {
    public static void initialize(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> fieldType = field.getType();
                Object dependency = fieldType.getDeclaredConstructor().newInstance();
                field.setAccessible(true);
                field.set(object, dependency);
            }
        }
    }
}
```

---

**Использование контейнера**

```java
public class Application {
    public static void main(String[] args) {
        try {
            ServiceB serviceB = new ServiceB();
            DIContainer.initialize(serviceB);
            serviceB.performAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

- Отделение создания объектов от их использования.
- Улучшение тестируемости и модульности кода.

# Зачем?

## Гибкость и расширяемость

- **Плагины и модули**: Позволяет создавать системы, которые могут быть расширены без изменения основного кода.
- **Конфигурация в рантайме**: Классы могут быть загружены и созданы на основе внешних конфигураций или пользовательского ввода.

## Фреймворки и библиотеки

- **Внедрение зависимостей (DI)**: Автоматическое создание и связывание объектов без явного указания в коде.
- **Объектно-реляционное отображение (ORM)**: Создание объектов на основе данных из базы данных.
- **Сериализация и десериализация**: Обработка объектов без знания их типов на этапе компиляции.

## Инструменты разработки

- **Тестирование**: Создание объектов для тестов без жесткого связывания с конкретными классами.
- **Отладка и профилирование**: Динамическое создание и анализ объектов для мониторинга производительности.

## Динамические прокси и AOP

- **Перехват вызовов методов**: Создание прокси-объектов, которые могут добавлять дополнительную логику при вызове методов.
