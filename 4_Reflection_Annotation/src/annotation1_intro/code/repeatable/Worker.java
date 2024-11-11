package annotation1_intro.code.repeatable;

public class Worker {

    @Schedule(day = "Понедельник")
    @Schedule(day = "Среда")
    @Schedule(day = "Пятница")
    public void work() {
        // Реализация
    }
}