package restriction;

import java.util.List;

public class OverRestrictionMain {
    // Метод для суммирования всех элементов списка, который принимает любой тип, наследующий Number
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);

        System.out.println(sumOfList(intList));    // Вывод: 6.0
        System.out.println(sumOfList(doubleList)); // Вывод: 6.6
    }
}
