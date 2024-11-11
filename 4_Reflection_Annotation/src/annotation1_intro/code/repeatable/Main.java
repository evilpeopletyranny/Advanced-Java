package annotation1_intro.code.repeatable;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Worker.class.getMethod("work");

        // Получение повторяющихся аннотаций напрямую
        Schedule[] schedules = method.getAnnotationsByType(Schedule.class);
        for (Schedule schedule : schedules) {
            System.out.println("День работы: " + schedule.day());
        }

        // Получение контейнерной аннотации
        Schedules schedulesContainer = method.getAnnotation(Schedules.class);
        if (schedulesContainer != null) {
            for (Schedule schedule : schedulesContainer.value()) {
                System.out.println("День работы (из контейнера): " + schedule.day());
            }
        }
    }
}
