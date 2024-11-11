# Spliterator

**Spliterator** (от слов Splitable Iterator) — это специальный интерфейс в Java, представленный в Java 8 вместе со *
*Stream API**. Он предназначен для последовательного или параллельного перебора элементов источника данных, поддерживая
эффективную обработку больших объемов данных.

## Определение

```Spliterator``` — это интерфейс, который представляет собой улучшенную версию итератора (```Iterator```), с
возможностью разделения данных на части для параллельной обработки. Он также предоставляет информацию о характеристиках
данных, что позволяет оптимизировать выполнение операций.

## Основные методы

- ```tryAdvance(Consumer<? super T> action)```: выполняет действие над следующим элементом, если он есть, и продвигается
  вперед.
- ```trySplit()```: разделяет данные на две части, возвращая новый Spliterator для одной из частей или ```null```, если
  разделение невозможно.
- ```estimateSize()```: возвращает приблизительное количество оставшихся элементов.
- ```characteristics()```: возвращает битовую маску характеристик Spliterator, таких
  как ```ORDERED```, ```DISTINCT```, ```SORTED``` и т.д.
    - ```ORDERED```: элементы имеют определенный порядок.
    - ```DISTINCT```: элементы уникальны.
    - ```SORTED```: элементы отсортированы.
    - ```SIZED```: известен точный размер.
    - ```NONNULL```: элементы не являются null.
    - ```IMMUTABLE```: данные не меняются.
    - ```CONCURRENT```: безопасен для использования из нескольких потоков без дополнительной синхронизации.
    - ```SUBSIZED```: все полученные из trySplit() Spliterator также имеют характеристику SIZED.

# SplitIterator и Stream API

## Создание Stream с помощью Spliterator

**Stream API** использует Spliterator для итерации по элементам источника данных. При создании потока из коллекции или
другого источника, Java автоматически создает соответствующий Spliterator.

- **Коллекции**: имеют метод ```spliterator()```, возвращающий Spliterator для коллекции.

```
List<String> list = new ArrayList<>();
Spliterator<String> spliterator = list.spliterator();
```

- Создание Stream из Spliterator:

```
Stream<String> stream = StreamSupport.stream(spliterator, false);
```

## Параллельная обработка

Spliterator поддерживает разделение данных для параллельной обработки. Метод ```trySplit()``` позволяет разбить данные
на подзадачи, что облегчает распараллеливание операций в Stream API.

# Как с помощью Spliterator реализовать собственный Stream

Вы можете создать собственный Stream, реализовав свой Spliterator и используя ```StreamSupport.stream()```. Это полезно,
когда вы работаете с нестандартными источниками данных или хотите контролировать процесс итерации.

1. **Реализовать свой Spliterator**, реализуя интерфейс ```Spliterator<T>```.
2. **Использовать** ```StreamSupport.stream()```, чтобы создать Stream на основе вашего Spliterator.

# Пример: Создание Stream для обхода файловой системы

Предположим, необходимо создать Stream для обхода файловой системы и обработки файлов.

### Шаг 1: Реализация собственного Spliterator

```java
import java.io.File;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FileSpliterator implements Spliterator<File> {
    private final Deque<File> files = new ArrayDeque<>();

    public FileSpliterator(File rootDirectory) {
        files.add(rootDirectory);
    }

    @Override
    public boolean tryAdvance(Consumer<? super File> action) {
        if (files.isEmpty()) return false;

        File file = files.poll();
        action.accept(file);

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) Collections.addAll(files, children);
        }

        return true;
    }

    //Парарельная обработка - создание дополнительных итераторов
    @Override
    public Spliterator<File> trySplit() {
        int size = files.size();
        if (size <= 1) return null;

        Deque<File> splitFiles = new ArrayDeque<>();
        for (int i = 0; i < size / 2; i++) splitFiles.add(files.poll());

        return new FileSpliterator(splitFiles);
    }

    private FileSpliterator(Deque<File> files) {
        this.files.addAll(files);
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE; // Неизвестный размер
    }

    @Override
    public int characteristics() {
        return NONNULL | IMMUTABLE;
    }
}
```

- **Конструктор**: принимает корневую директорию и добавляет ее в очередь ```files```.
- ```tryAdvance()```: извлекает файл из очереди и выполняет действие action. Если файл — директория, добавляет ее
  содержимое в очередь.
- ```trySplit()```: разбивает очередь файлов на две части для параллельной обработки.
- ```estimateSize()```: возвращает приблизительный размер (в данном случае неизвестен).
- ```characteristics()```: указывает характеристики Spliterator.

### Шаг 2: Создание Stream из Spliterator

```java
import java.io.File;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileStreamExample {
    public static void main(String[] args) {
        File rootDir = new File("src");

        Spliterator<File> fileSpliterator = new FileSpliterator(rootDir);
        Stream<File> fileStream = StreamSupport.stream(fileSpliterator, true); // true для параллельного потока

        fileStream
                .filter(File::isFile)
                .forEach(file -> System.out.println(file.getAbsolutePath()));
    }
}
```

# Советы по реализации Spliterator

- **Хорошо продумайте** ```trySplit()```: эффективное разделение данных критично для параллельной обработки.
- **Указывайте правильные характеристики**: это помогает Stream API оптимизировать выполнение.
- **Обрабатывайте исключения корректно**: избегайте выброса непроверяемых исключений внутри ```tryAdvance()``` и других
  методов.
- **Тестируйте ваш Spliterator**: убедитесь, что он корректно обрабатывает все случаи, особенно при параллельном
  выполнении.

# Заключение

**Spliterator** — мощный инструмент в Java для работы с потоками данных, особенно в контексте **Stream API**. Понимание
того, как работает Spliterator и как его использовать для создания собственных Stream, позволяет разработчикам
эффективно обрабатывать данные из различных источников, оптимизировать производительность и расширять возможности
приложения.
