# Особенность Java - Функциональные интерфейсы

## Объект первого порядка

**Объект первого класса в программировании** — это сущность, которая поддерживается языком программирования на уровне,
позволяющем использовать ее так же гибко, как и любые другие объекты. Это означает, что такие объекты могут:

- Присваиваться переменным или структурам данных.
- Передаваться в функции в качестве аргументов.
- Возвращаться из функций как результаты.
- Создаваться во время выполнения программы.

Объекты первого класса повышают гибкость и выразительность языка программирования. Они позволяют реализовывать
функциональные парадигмы программирования, такие как функции высшего порядка и замыкания, что способствует написанию
более модульного и переиспользуемого кода.

## Функциональный интерфейс

**Функциональный интерфейс** в Java — это интерфейс, который содержит **ровно один абстрактный метод**. Такие интерфейсы
служат типами для лямбда-выражений и ссылок на методы. Они позволяют передавать поведение в методы и переменные, делая
код более функциональным и лаконичным.

```java

@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}
```

### Зачем?

В Java до версии 8 не было поддержки лямбда-выражений и функционального программирования. При добавлении этих
возможностей в Java 8 возникла необходимость представлять функции как объекты, соответствующие существующей системе
типов, основанной на классах и интерфейсах.

- **Совместимость с существующей системой типов:** Java — строго типизированный язык, и все типы должны быть объявлены.
  Поскольку **функции не были объектами первого класса**, было решено использовать интерфейсы для представления
  функциональных типов.
- **Интеграция с существующим кодом:** Функциональные интерфейсы позволяют использовать лямбда-выражения в местах, где
  ожидается реализация интерфейса с одним абстрактным методом, что облегчает интеграцию нового функционала с уже
  существующим кодом.
- **Понятность и ясность:** Использование интерфейсов делает код более понятным, так как интерфейс определяет контракт —
  какой метод должен быть реализован.

### Как это работает?

Когда вы используете лямбда-выражение в Java, компилятор сопоставляет его с функциональным интерфейсом, который
ожидается в данном контексте.

```
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println(name));
```

В методе ```forEach``` ожидается параметр типа ```Consumer<? super T>```, который является функциональным интерфейсом с
методом ```void accept(T t)```. Лямбда-выражение ```name -> System.out.println(name)``` соответствует этому интерфейсу.

## А что в других JVM-based языках

**Kotlin** и **Scala** изначально разработаны с поддержкой функционального программирования и имеют **функции как
объекты первого класса**. Это означает, что функции могут быть присвоены переменным, переданы в качестве аргументов и
возвращены из других функций без необходимости использования дополнительных интерфейсов или классов.

---

### Kotlin

В Kotlin функции имеют собственные типы, записываемые как ```(A, B) -> C```, где ```A``` и ```B``` — типы параметров,
а ```C``` — возвращаемый тип.

```
val sum: (Int, Int) -> Int = { a, b -> a + b }
val result = sum(5, 3) // result = 8
```

### Scala

В Scala функции также являются объектами первого класса, и функциональные типы записываются как ```(A, B) => C```.

```
val sum: (Int, Int) => Int = (a, b) => a + b
val result = sum(5, 3) // result = 8
```

---

Поскольку функции в Kotlin и Scala имеют собственные типы, нет необходимости создавать специальные интерфейсы для их
представления. Компилятор и язык напрямую работают с функциональными типами.

- **Лаконичность кода:** Нет необходимости объявлять дополнительные интерфейсы.
- **Гибкость:** Возможность легко использовать функции с любым количеством параметров без создания новых интерфейсов.
- **Вывод типов:** Компилятор может автоматически выводить типы функций на основе контекста.

## Разница системы типов Java и Kotlin/Scala

### Java

- **Классы и интерфейсы как основные строительные блоки:** В Java система типов основана на классах и интерфейсах.
- **Отсутствие функций как объектов первого класса:** Функции не могут существовать независимо от классов или
  интерфейсов
- **Функциональные интерфейсы как компромисс:** Для внедрения функционального программирования в Java были введены
  функциональные интерфейсы.

### Kotlin/Scala

- **Функции как объекты первого класса:** Функции могут быть присвоены переменным, переданы и возвращены без оберток.
- **Богатая система типов:** Поддержка функциональных типов встроена в язык.
- **Вывод типов и лямбда-выражения:** Компилятор способен выводить типы функций, что упрощает синтаксис.

## Примеры

### Java с функциональным интерфейсом

```java

@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator sum = (a, b) -> a + b;
        int result = sum.calculate(5, 3);
        System.out.println(result); // Вывод: 8
    }
}
```

### Kotlin без функционального интерфейса

```kotlin
fun main() {
    val sum: (Int, Int) -> Int = { a, b -> a + b }
    val result = sum(5, 3)
    println(result) // Вывод: 8
}
```

### Scala без функционального интерфейса

```scala
object Main extends App {
  val sum: (Int, Int) => Int = (a, b) => a + b
  val result = sum(5, 3)
  println(result) // Вывод: 8
}
```

## Выводы

Функциональные интерфейсы в Java необходимы для представления функций в системе типов, основанной на классах и
интерфейсах. Они позволяют использовать лямбда-выражения и функциональный стиль программирования, сохраняя при этом
совместимость с существующим кодом и принципами языка.

В Kotlin и Scala функции являются объектами первого класса и имеют встроенные функциональные типы. Это позволяет
использовать функции без дополнительных интерфейсов, делая код более простым и выразительным. Компилятор этих языков
способен выводить типы функций, что упрощает написание и чтение кода.