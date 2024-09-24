package gen5_inheritance.code.example2;

// Класс для демонстрации использования Box и ColorBox
public class BoxMain {
    public static void main(String[] args) {
        // Создание экземпляра Box для хранения Integer
        Box<Integer> integerBox = new Box<>(123);
        integerBox.displayContent(); // Вывод: Box contains: 123

        // Создание экземпляра ColorBox для хранения String
        ColorBox<String> stringColorBox = new ColorBox<>("Hello, Generics!", "Red");
        stringColorBox.displayContent(); // Вывод: ColorBox contains: Hello, Generics! and is Red

        // Изменение содержимого и цвета ColorBox
        stringColorBox.setContent("New Content");
        stringColorBox.setColor("Blue");
        stringColorBox.displayContent(); // Вывод: ColorBox contains: New Content and is Blue
    }
}
