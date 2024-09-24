package gen5_inheritance.code.example2;

// Подкласс ColorBox, наследующий Box и добавляющий свойство color
public class ColorBox<T> extends Box<T> {
    private String color;

    // Конструктор для инициализации содержимого и цвета
    public ColorBox(T content, String color) {
        super(content); // Вызов конструктора родительского класса
        this.color = color;
    }

    // Геттер для получения цвета
    public String getColor() {
        return color;
    }

    // Сеттер для изменения цвета
    public void setColor(String color) {
        this.color = color;
    }

    // Переопределение метода для отображения содержимого с цветом
    @Override
    public void displayContent() {
        System.out.println("ColorBox contains: " + getContent() + " and is " + color);
    }
}