package gen5_inheritance.code.example1;

/**
 * Автомобиль, который является траспортным средством.
 * При этом явно указно, что идентификатор путем строкой.
 */
public class Car extends Vehicle<String> {
    private int numberOfDoors;

    public Car(String id, String name, int numberOfDoors) {
        super(id, name);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

