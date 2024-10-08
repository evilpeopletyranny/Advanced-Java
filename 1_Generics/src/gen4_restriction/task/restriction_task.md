# Задание

Вам нужно реализовать простую систему для управления фигурами с использованием Java Generics, ограничения сверху (
```extends```) и ограничения снизу (```super```). Система должна позволять добавлять фигуры в коллекцию и рисовать их.
Основная цель задания — правильно использовать ограничения Generics для управления типами в обобщенных методах.

1. **Создайте интерфейс** Shape, который будет представлять фигуру. У интерфейса должен быть метод **draw()**,
   который выводит на экран сообщение, что фигура рисуется.
2. **Создайте несколько разновидностей фигур**, которые реализуют
   интерфейс ```Shape```: ```Circle```, ```Rectangle```, ```Triagnle```. Переопределите метод draw() для каждой фигуры,
   чтобы он выводил название рисуемой фигуры.
3. **Создайте обобщенный класс - компановщик** ```Canvas```, который будет управлять коллекцией
   фигур (```List<Shape>```).
4. **Реализуйте метод** ```addShape(List<? super Shape> list, Shape shape)```, который добавляет фигуры в коллекцию,
   используя ограничение снизу (```super```).
5. **Реализуйте метод** ```drawShapes(List<? extends Shape> list)```, который будет принимать коллекцию фигур с
   ограничением сверху (```extends```) и рисовать все фигуры в списке, вызывая метод ```draw()```.

_Пояснение: ```Canvas``` не содержит внутри списка, а работает с внешним списком, добавляя в него элементы. Как в
примерах._

### [Проверка](ShapeMain.java):

Раскоментируйте код в классе ```ShapeMain``` - он должен отработать без ошибок.

## Время: 20-25 минут