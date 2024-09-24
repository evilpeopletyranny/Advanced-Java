package gen4_restriction.code;

import java.util.ArrayList;
import java.util.List;

//Абстрактный класс Фигур
//Содержит реализацию метода строкового представления
abstract class Shape {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

// Интерфейс Drawing
interface Drawing {
    void draw();
}

// Класс Circle, наследующий Shape, реализующий интерфейс Drawing
class Circle extends Shape implements Drawing {
    @Override
    public void draw() {
        System.out.println("Draw circle");
    }
}

// Класс Rectangle, наследующий Shape, реализующий интерфейс Drawing
class Rectangle extends Shape implements Drawing {
    @Override
    public void draw() {
        System.out.println("Draw circle");
    }
}

public class BelowRestriction {
    // Метод для добавления фигур в список, который принимает Shape и его суперклассы/классы его реализующие
    public static void addShapes(List<? super Drawing> shapes) {
        // Мы можем безопасно добавлять элементы типа Shape и его подклассов
        shapes.add(new Circle());
        shapes.add(new Rectangle());

        // Ошибка компиляции: нельзя добавить элемент не подходящего типа
        // shapes.add("String"); // String не является Shape или его суперклассом
    }

    public static void main(String[] args) {
        // Список типа Shape
        List<Drawing> shapeList = new ArrayList<>();
        addShapes(shapeList);
        System.out.println("Shape List: " + shapeList); // Вывод: Shape List: [Circle, Rectangle, Shape]

        // Список типа Object, который является суперклассом всех классов
        List<Object> objectList = new ArrayList<>();
        addShapes(objectList);
        System.out.println("Object List: " + objectList); // Вывод: Object List: [Circle, Rectangle, Shape]

        // Пример, который не сработает, так как List<Circle> не является суперклассом Shape
        // List<Circle> circleList = new ArrayList<>();
        // addShapes(circleList); // Ошибка компиляции
    }
}
