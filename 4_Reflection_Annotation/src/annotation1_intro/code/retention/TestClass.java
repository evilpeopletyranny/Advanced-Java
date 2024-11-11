package annotation1_intro.code.retention;

public class TestClass {
    @MyAnnotation("Текст из аннотации")
    public void myMethod() {
        System.out.println("Метод TestClass.myMethod()");
    }
}
