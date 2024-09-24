package gen5_inheritance.code.example1;

public class VehicleMain {
    public static void main(String[] args) {
        // Создание экземпляра Vehicle с идентификатором Integer
        Vehicle<Integer> bike = new Vehicle<>(101, "Mountain Bike");
        bike.displayInfo();

        System.out.println();

        // Создание экземпляра Car с идентификатором String
        Car car = new Car("CAR-001", "Sedan", 4);
        car.displayInfo();
    }
}
