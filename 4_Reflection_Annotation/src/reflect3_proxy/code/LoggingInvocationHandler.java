package reflect3_proxy.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target; // Реальный объект, вызовы к которому будут перехватываться

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Логирование перед вызовом метода
        System.out.println("Вызов метода: " + method.getName() + ", с аргументами: " + Arrays.toString(args));

        Object result;
        try {
            // Вызов реального метода на целевом объекте
            result = method.invoke(target, args);

            // Логирование после успешного выполнения метода
            System.out.println("Метод " + method.getName() + " выполнен успешно, результат: " + result);
        } catch (Throwable t) {
            // Логирование в случае исключения
            System.out.println("Метод " + method.getName() + " выбросил исключение: " + t.getCause());
            throw t;
        }

        return result;
    }
}