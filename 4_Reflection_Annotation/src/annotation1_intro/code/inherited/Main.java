package annotation1_intro.code.inherited;

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = SubClass.class;
        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
        if (annotation != null) {
            System.out.println("Аннотация унаследована: " + annotation.value());
        } else {
            System.out.println("Аннотация не найдена");
        }
    }
}
