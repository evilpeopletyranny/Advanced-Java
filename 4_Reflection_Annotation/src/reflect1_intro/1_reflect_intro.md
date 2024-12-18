# Reflection API

**Рефлексия** — это способность программы анализировать и модифицировать свою собственную структуру и поведение во время
выполнения. В контексте Java рефлексия предоставляет API для доступа к метаданным классов, методов, полей и
конструкторов, а также для их динамического вызова и изменения.

## Как это работает?

- **Получение информации о классах и объектах**: с помощью класса ```Class``` и Reflection API можно получить информацию
  о структуре класса, его методах, полях и конструкторах.
- **Динамическое создание объектов**: рефлексия позволяет создавать экземпляры классов во время выполнения без явного
  указания их типов на этапе компиляции.
- **Динамический вызов методов**: методы класса могут быть вызваны динамически, даже если их имена не известны заранее.
- **Доступ к полям**: можно читать и изменять значения полей, включая приватные, обходя ограничения модификаторов
  доступа.

# Основные компоненты Reflection API

# Class<?>

```Class<?>``` - центральный класс рефлексии, представляющий информацию о классе или интерфейсе.

### Способ 1: Использование .class

```
Class<String> stringClass = String.class;
```

---

### Способ 2: Использование метода getClass()

```
String str = "Hello, World!";
Class<? extends String> stringClass = str.getClass();
```

---

### Способ 3: Использование метода Class.forName()

```
try {
    Class<?> stringClass = Class.forName("java.lang.String");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

---

### Зачем это может быть нужно

- **Динамическая загрузка классов**: Позволяет загружать классы по имени во время выполнения, что полезно при работе с
  плагинами или модульными системами.
- **Инспекция классов**: Позволяет исследовать структуру класса (методы, поля, конструкторы) для генерации документации,
  сериализации или десериализации объектов.
- **Создание экземпляров классов**: Можно создавать объекты, не зная типов на этапе компиляции.
- **Фреймворки и библиотеки**: Многие фреймворки (например, Spring, Hibernate) используют рефлексию для внедрения
  зависимостей и маппинга объектов.

# Field

```Field``` - представляет поле класса (переменную).

---

### Способ 1: Получение всех публичных полей (включая наследованные)

```
Class<?> clazz = MyClass.class;
Field[] fields = clazz.getFields();
```

---

### Способ 2: Получение всех объявленных полей (включая приватные)

```
Field[] declaredFields = clazz.getDeclaredFields();
```

---

### Способ 3: Получение конкретного поля по имени

```
try {
    Field field = clazz.getDeclaredField("fieldName");
} catch (NoSuchFieldException e) {
    e.printStackTrace();
}
```

---

### Зачем это может быть нужно

- **Доступ к значениям полей**: Позволяет читать и изменять значения полей объектов, даже если они приватные (с
  помощью ```setAccessible(true)```).
- **Сериализация и десериализация**: Используется для автоматического преобразования объектов в поток байтов и обратно.
- **Валидация данных**: Можно проверять значения полей на соответствие определенным условиям или аннотациям.
- **Генерация пользовательских отображений**: Например, для отображения объектов в UI или преобразования в другой
  формат (JSON, XML).

# Method

```Method``` - представляет метод класса.

### Способ 1: Получение всех публичных методов (включая наследованные)

```
Method[] methods = clazz.getMethods();
```

---

### Способ 2: Получение всех объявленных методов (включая приватные)

```
Method[] declaredMethods = clazz.getDeclaredMethods();
```

---

### Способ 3: Получение конкретного метода по имени и параметрам

```
try {
    Method method = clazz.getMethod("methodName", ParameterType1.class, ParameterType2.class);
} catch (NoSuchMethodException e) {
    e.printStackTrace();
}
```

### Зачем это может быть нужно

- **Динамический вызов методов**: Позволяет вызывать методы, имена которых неизвестны на этапе компиляции.
- **Инструменты тестирования**: Тестовые фреймворки (например, JUnit) используют рефлексию для вызова тестовых методов.
- **Инспекция и логирование**: Можно анализировать и логировать вызовы методов для отладки или мониторинга.
- **Реализация шаблонов проектирования**: Например, шаблон "Команда" или "Стратегия" могут использовать рефлексию для
  вызова методов по имени.

# Constructor

```Constructor``` - представляет конструктор класса.

### Способ 1: Получение всех публичных конструкторов

```
Constructor<?>[] constructors = clazz.getConstructors();
```

---

### Способ 2: Получение всех объявленных конструкторов (включая приватные)

```
Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
```

### Способ 3: Получение конкретного конструктора по параметрам

```
try {
    Constructor<MyClass> constructor = clazz.getConstructor(String.class, int.class);
} catch (NoSuchMethodException e) {
    e.printStackTrace();
}
```

### Зачем это может быть нужно

- **Создание экземпляров классов**: Позволяет создавать объекты динамически, выбирая нужный конструктор.
- **Доступ к приватным конструкторам**: Можно создавать экземпляры, даже если конструктор имеет ограниченный доступ (
  после ```setAccessible(true)```).
- **Фабричные методы и паттерны**: Используется в реализации паттернов проектирования, таких как "Фабрика" или "
  Прототип".
- **Инструменты сериализации**: При десериализации объектов может потребоваться вызвать конструктор без параметров или с
  определенными параметрами.

Array

```Modifier``` - предоставляет информацию о модификаторах доступа (public, private, static и т.д.).

Метод ```getModifiers()``` из Reflection API в Java возвращает целочисленное значение типа int, которое представляет собой
битовую маску модификаторов доступа и свойств класса, метода или поля. Эти значения можно расшифровать с помощью класса
java.lang.reflect.Modifier.

### Получение модификаторов класса

```
int classModifiers = clazz.getModifiers();
```

---

### Получение модификаторов метода

```
Method method = clazz.getMethod("methodName");
int methodModifiers = method.getModifiers();
```

---

### Получение модификаторов поля

```
Field field = clazz.getDeclaredField("fieldName");
int fieldModifiers = field.getModifiers();
```

---

### Проверка модификаторов

```
boolean isPublic = Modifier.isPublic(methodModifiers);
boolean isStatic = Modifier.isStatic(fieldModifiers);
```

---

# Array

```Array``` - утилиты для работы с массивами через рефлексию.

### Создание массива через рефлексию

```
int length = 10;
Class<?> componentType = String.class;
Object array = Array.newInstance(componentType, length);
```

---

### Установка и получение элементов массива

```
Array.set(array, 0, "Hello");
String element = (String) Array.get(array, 0);
```

---

### Определение длины массива

```
int arrayLength = Array.getLength(array);
```

---

### Зачем это может быть нужно

- **Динамическое создание массивов**: Когда тип элементов и размер массива неизвестны на этапе компиляции.
- **Обработка обобщенных типов**: При работе с дженериками и необходимо создавать массивы обобщенного типа.
- **Сериализация и десериализация**: При преобразовании массивов объектов в другой формат.
- **Инспекция объектов**: Позволяет работать с объектами, которые являются массивами, без знания их типа.
