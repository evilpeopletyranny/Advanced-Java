# List

**Интерфейс** ```List<E>``` представляет собой упорядоченную коллекцию, которая допускает хранение дубликатов элементов.
Каждый элемент в списке имеет уникальный индекс, начиная с 0, что позволяет обращаться к элементам по их
позиции. ```List<E>``` является частью Java Collections Framework и расширяет интерфейс ```Collection<E>```,
предоставляя дополнительные методы для работы с позиционным доступом к элементам.

## Ключевые характеристики:

- **Упорядоченность:** Элементы хранятся в определенном порядке, и этот порядок сохраняется при добавлении или удалении
  элементов.
- **Поддержка дубликатов:** Один и тот же элемент может присутствовать в списке несколько раз.
- **Индексация:** Каждый элемент имеет индекс, позволяющий быстро обращаться к нему.
- **Динамическое изменение размера:** Большинство реализаций списка поддерживают динамическое изменение размера,
  автоматически расширяясь или сужаясь по мере добавления или удаления элементов.

## Основные характеристики и методы

Интерфейс ```List<E>``` определяет ряд методов для управления элементами списка, их добавления, удаления, поиска и
доступа по индексу. Рассмотрим основные из них.

### Добавление элементов

```
boolean add(E e);
```

Добавляет элемент e в конец списка. Возвращает ```true```, если список изменился в результате операции.

```
void add(int index, E element);
```

Вставляет элемент ```element``` в список по указанному индексу ```index```. Остальные элементы сдвигаются вправо.

```
boolean addAll(Collection<? extends E> c)
```

Добавляет все элементы из коллекции ```c``` в конец списка. Возвращает ```true```, если список изменился.

```
boolean addAll(int index, Collection<? extends E> c);
```

Вставляет все элементы из коллекции ```c``` в список, начиная с указанного индекса ```index```. Возвращает ```true```,
если список изменился.

### Удаление элементов

```
boolean remove(Object o);
```

Удаляет первый экземпляр указанного элемента ```o``` из списка, если он присутствует. Возвращает ```true```, если
элемент был удален.

```
E remove(int index)
```

Удаляет элемент по указанному индексу index и возвращает его.

```
boolean removeAll(Collection<?> c);
```

Удаляет из списка все элементы, которые присутствуют в коллекции ```c```. Возвращает ```true```, если список изменился.

```
boolean retainAll(Collection<?> c);
```

Оставляет в списке только те элементы, которые присутствуют в коллекции ```c```. Возвращает ```true```, если список
изменился.

### Доступ к элементам

```
E get(int index);
```

Возвращает элемент, находящийся по указанному индексу ```index```.

```
E set(int index, E element)
```

Заменяет элемент на позиции ```index``` на новый элемент ```element```. Возвращает старый элемент.

### Поиск элементов

```
int indexOf(Object o);
```

Возвращает индекс первого вхождения элемента ```o``` в списке. Если элемент не найден, возвращает ```-1```.

```
int lastIndexOf(Object o);
```

Возвращает индекс последнего вхождения элемента ```o``` в списке. Если элемент не найден, возвращает ```-1```.

### Размер и состояние списка

```
int size();
```

Возвращает количество элементов в списке.

```
boolean isEmpty();
```

Проверяет, пустой ли список. Возвращает ```true```, если список не содержит элементов.

```
boolean contains(Object o);
```

Проверяет, содержит ли список указанный элемент ```o```. Возвращает ```true```, если элемент присутствует.

### Итерация по списку

```
Iterator<E> iterator();
```

Возвращает итератор для последовательного перебора элементов списка.

```
ListIterator<E> listIterator();
```

Возвращает двунаправленный итератор для перебора элементов списка.

```
ListIterator<E> listIterator(int index);
```

Возвращает двунаправленный итератор, начинающий перебор с указанного идекса ```index```.

## Реализации интерфейса List<E>

Интерфейс ```List<E>``` имеет несколько основных реализаций, каждая из которых обладает своими особенностями и
предназначена для различных сценариев использования. Рассмотрим наиболее популярные из
них: ```ArrayList<E>```, ```LinkedList<E>```, ```Vector<E>```, и ```Stack<E>```.

# ArrayList<E>

```ArrayList<E>``` представляет собой динамический массив, который может изменять свой размер автоматически. Это
наиболее часто используемая реализация интерфейса ```List<E>```, обеспечивающая быстрый доступ к элементам по индексу.

## Ключевые особенности:

- **Быстрый доступ по индексу:** Операции ```get(int index)``` и ```set(int index, E element)``` выполняются за
  константное время ```(O(1))```.
- **Динамическое изменение размера:** Массив автоматически расширяется при добавлении новых элементов.
- **Неэффективные вставки и удаления:** Операции вставки и удаления элементов в середину списка требуют сдвига остальных
  элементов, что занимает линейное время ```(O(n))```.

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // Создание ArrayList для хранения строк
        List<String> fruits = new ArrayList<>();

        // Добавление элементов
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");

        // Добавление элемента по индексу
        fruits.add(2, "Elderberry"); // Вставляет "Elderberry" на позицию 2

        // Отображение списка
        System.out.println("Fruits List: " + fruits);

        // Доступ к элементу по индексу
        String fruit = fruits.get(3);
        System.out.println("Fruit at index 3: " + fruit); // Вывод: Date

        // Изменение элемента
        fruits.set(1, "Blueberry"); // Заменяет "Banana" на "Blueberry"
        System.out.println("Updated Fruits List: " + fruits);

        // Удаление элемента
        fruits.remove("Cherry");
        System.out.println("After Removal: " + fruits);

        // Итерация по списку с помощью for-each
        System.out.println("\nIterating through the list:");
        for (String f : fruits) {
            System.out.println(f);
        }
    }
}
```

# LinkedList<E>

```LinkedList<E>``` реализует интерфейс ```List<E>``` и представляет собой двунаправленный связанный список. Каждый
элемент списка содержит ссылки на предыдущий и следующий элементы, что обеспечивает эффективные операции вставки и
удаления элементов в любой позиции списка.

## Ключевые особенности:

- **Эффективные вставки и удаления:** Операции вставки и удаления элементов в начале или середине списка выполняются за
  константное время ```(O(1))```.
- **Медленный доступ по индексу:** Операции ```get(int index)``` и ```set(int index, E element)``` требуют линейного
  времени ```(O(n))```, так как нужно последовательно пройти до нужного индекса.
- **Двойные ссылки:** Каждый элемент знает своего предыдущего и следующего соседа, что облегчает итерацию в обеих
  направлениях.

```java
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        // Создание LinkedList для хранения целых чисел
        List<Integer> numbers = new LinkedList<>();

        // Добавление элементов
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        // Добавление элемента в начало списка
        numbers.add(0, 5); // Вставляет 5 на позицию 0

        // Добавление элемента в конец списка
        numbers.add(numbers.size(), 50); // Вставляет 50 в конец

        // Отображение списка
        System.out.println("Numbers List: " + numbers);

        // Доступ к элементу по индексу
        int num = numbers.get(3);
        System.out.println("Number at index 3: " + num); // Вывод: 30

        // Изменение элемента
        numbers.set(2, 25); // Заменяет 20 на 25
        System.out.println("Updated Numbers List: " + numbers);

        // Удаление элемента
        numbers.remove(Integer.valueOf(40)); // Удаляет первый экземпляр 40
        System.out.println("After Removal: " + numbers);

        // Итерация по списку с помощью for-each
        System.out.println("\nIterating through the list:");
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
```

# Vector<E>

```Vector<E>``` — устаревшая реализация интерфейса ```List<E>```, которая поддерживает синхронизацию, обеспечивая
потокобезопасность. В отличие от ```ArrayList<E>```, все его методы синхронизированы, что делает его безопасным для
использования в многопоточной среде без дополнительной синхронизации.

## Ключевые особенности:

- **Синхронизированность:** Все методы ```Vector``` являются синхронизированными, что предотвращает одновременный доступ
  из нескольких потоков.
- **Динамическое изменение размера:** Как и ```ArrayList```, Vector автоматически расширяется при добавлении элементов.
- **Устаревшая реализация:** В большинстве случаев рекомендуется использовать ```ArrayList<E>```, если не требуется
  явная потокобезопасность.

```java
import java.util.List;
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        // Создание Vector для хранения объектов класса String
        List<String> cities = new Vector<>();

        // Добавление элементов
        cities.add("New York");
        cities.add("Los Angeles");
        cities.add("Chicago");
        cities.add("Houston");

        // Добавление элемента по индексу
        cities.add(2, "San Francisco"); // Вставляет "San Francisco" на позицию 2

        // Отображение Vector
        System.out.println("Cities Vector: " + cities);

        // Доступ к элементу по индексу
        String city = cities.get(1);
        System.out.println("City at index 1: " + city); // Вывод: Los Angeles

        // Изменение элемента
        cities.set(3, "Phoenix"); // Заменяет "Chicago" на "Phoenix"
        System.out.println("Updated Cities Vector: " + cities);

        // Удаление элемента
        cities.remove("Houston");
        System.out.println("After Removal: " + cities);

        // Итерация по Vector с помощью for-each
        System.out.println("\nIterating through the Vector:");
        for (String c : cities) {
            System.out.println(c);
        }
    }
}
```

## Альтернативы для потокобезопасности

### Collections.synchronizedList

```Collections.synchronizedList``` — это обертка (декоратор), предоставляющая потокобезопасную версию любой коллекции,
реализующей
интерфейс ```List<E>```. Она синхронизирует доступ к внутреннему списку, обеспечивая безопасность при многопоточном
доступе.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedListExample {
    public static void main(String[] args) {
        // Создание обычного ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");

        // Создание синхронизированного списка
        List<String> synchronizedList = Collections.synchronizedList(arrayList);

        // Добавление элементов в синхронизированный список
        synchronizedList.add("Date");
        synchronizedList.add("Elderberry");

        // Итерация по синхронизированному списку
        synchronized (synchronizedList) { // Необходим блок синхронизации
            Iterator<String> iterator = synchronizedList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        // Пример многопоточного доступа
        Runnable task = () -> {
            synchronizedList.add("Fig");
            System.out.println("Added Fig");
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
```

### CopyOnWriteArrayList

```CopyOnWriteArrayList``` — это потокобезопасная реализация интерфейса ```List<E>```, которая использует стратегию
копирования при записи. Это означает, что каждая операция добавления, удаления или изменения элемента создает новую
копию внутреннего массива.

- **Потокобезопасность без явной синхронизации:** Все операции являются атомарными и не требуют внешней синхронизации.
- **Высокая производительность при чтении:** Чтение данных выполняется очень быстро, так как доступ осуществляется к
  неизменяемой копии.
- **Низкая производительность при записи:** Из-за копирования массива при каждой модификации записи могут быть
  медленными, особенно при частых изменениях.
- **Безопасность итерации:** Итераторы не выбрасывают ```ConcurrentModificationException```, так как работают с
  моментальным снимком данных.

```java
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        // Создание CopyOnWriteArrayList
        List<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("Apple");
        cowList.add("Banana");
        cowList.add("Cherry");

        // Добавление элементов
        cowList.add("Date");
        cowList.add("Elderberry");

        // Итерация по списку без блокировки
        for (String fruit : cowList) {
            System.out.println(fruit);
            // Модификация списка во время итерации
            if (fruit.equals("Cherry")) {
                cowList.add("Fig");
            }
        }

        System.out.println("\nAfter iteration:");
        for (String fruit : cowList) {
            System.out.println(fruit);
        }

        // Пример многопоточного доступа
        Runnable readTask = () -> {
            for (String fruit : cowList) {
                System.out.println("Read: " + fruit);
            }
        };

        Runnable writeTask = () -> {
            cowList.add("Grape");
            System.out.println("Added Grape");
        };

        Thread thread1 = new Thread(readTask);
        Thread thread2 = new Thread(writeTask);

        thread1.start();
        thread2.start();
    }
}
```

**Когда использовать ```CopyOnWriteArrayList```:**

- Когда операции чтения преобладают над операциями записи.
- В случаях, когда требуется безопасная итерация без блокировок.
- Для неизменяемых или редко изменяемых списков в многопоточной среде.