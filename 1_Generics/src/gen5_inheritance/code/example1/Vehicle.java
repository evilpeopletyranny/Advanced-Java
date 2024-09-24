package gen5_inheritance.code.example1;

/**
 * Обощенный класс транспортного средства
 * @param <T> тип идентификатора
 */
public class Vehicle<T> {
    private T id;
    private String name;

    public Vehicle(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        System.out.println("Vehicle ID: " + id + ", Name: " + name);
    }
}