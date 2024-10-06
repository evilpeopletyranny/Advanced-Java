# Stack

В Java стандартной библиотеки интерфейс ```Stack``` как таковой отсутствует. Вместо него имеется класс ```Stack<E>```,
который является частью пакета ```java.util``` и представляет собой тип стека, работающего по принципу **LIFO** (
Last-In-First-Out, "последним пришел — первым ушел"). В этом классе предоставляются методы для добавления, удаления и
просмотра элементов, аналогичные поведению классического стека.

Однако стоит отметить, что ```Stack<E>``` является устаревшей (legacy) коллекцией, поскольку наследуется от класса
Vector<E>.
В современных приложениях рекомендуется использовать другие коллекции, такие как ```Deque<E>```, реализованные в
ArrayDeque<E>, которые обеспечивают более гибкую и эффективную работу.

## Ключевые характеристики:

- **LIFO**: Элементы добавляются и удаляются только с вершины стека.
- **Потокобезопасность**: Из-за наследования от Vector<E> все методы Stack<E> синхронизированы, что делает его
  безопасным для использования в многопоточной среде.
- Устаревший дизайн: ```Stack<E>``` считается устаревшим, и современные альтернативы, такие как ```Deque<E>```,
  предпочтительнее для новых проектов.

## Реализации

| Характеристика                      | `Stack<E>`                   | `ArrayDeque<E>`     | `LinkedList<E>`   |
|-------------------------------------|------------------------------|---------------------|-------------------|
| **Потокобезопасность**              | Да (наследует `Vector<E>`)   | Нет                 | Нет               |
| **Производительность**              | Низкая (из-за синхронизации) | Высокая             | Средняя           |
| **Структура данных**                | Массив (`Vector<E>`)         | Динамический массив | Двусвязный список |
| **Использование как очередь**       | Нет                          | Да                  | Да                |
| **Гибкость**                        | Ограниченная                 | Высокая             | Высокая           |
| **Предпочтение для новых проектов** | Нет                          | Да                  | Да                |

Для реализации стека в современных приложениях предпочтительнее использовать ```Deque<E>``` -
**двунаправленную очередь**, которая предоставляет гибкие возможности для работы с элементами как с конца, так и с
начала очереди.

# ArrayDeque<E>

```ArrayDeque<E>``` — это динамическая реализация интерфейса ```Deque<E>```, которая может использоваться как стек и как
очередь.

## Ключевые характеристики:

- **Высокая производительность:** Быстрое добавление и удаление элементов с обоих концов.
- **Нет встроенной синхронизации:** ```ArrayDeque<E>``` не потокобезопасен, что позволяет избежать накладных расходов на
  синхронизацию в однопоточных сценариях.

```
Deque<String> stack = new ArrayDeque<>();
stack.push("Apple");
stack.push("Banana");
stack.push("Cherry");

System.out.println(stack.pop()); // Cherry
```

# LinkedList<E>

```LinkedList<E>``` реализует интерфейсы ```List<E>``` и ```Deque<E>```, что позволяет использовать его как стек.

## Ключевые характеристики:

- **Двусвязный список:** Элементы хранятся в виде узлов с ссылками на предыдущий и следующий узел.
- **Гибкость:** Поддерживает все операции ```Deque<E>```, включая добавление и удаление элементов с обоих концов.

```
Deque<String> stack = new LinkedList<>();
stack.push("Apple");
stack.push("Banana");
stack.push("Cherry");

System.out.println(stack.pop()); // Cherry
```