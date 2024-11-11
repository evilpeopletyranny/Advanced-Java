package annotation1_intro.code.retention;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Получение аннотации во время выполнения
        try {
            Method method = TestClass.class.getMethod("myMethod");
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("Значение аннотации: " + annotation.value());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
