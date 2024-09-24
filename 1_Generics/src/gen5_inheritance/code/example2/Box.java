package gen5_inheritance.code.example2;

public class Box<T> {
    private T content;

    // Конструктор для инициализации содержимого
    public Box(T content) {
        this.content = content;
    }

    // Геттер для получения содержимого
    public T getContent() {
        return content;
    }

    // Сеттер для изменения содержимого
    public void setContent(T content) {
        this.content = content;
    }

    // Метод для отображения содержимого
    public void displayContent() {
        System.out.println("Box contains: " + content);
    }
}
