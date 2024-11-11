package reflect3_proxy.code;

public class Main {
    public static void main(String[] args) {
        // Создаем реальный объект
        Calculator calculator = new CalculatorImpl();

        // Создаем прокси для реального объекта
        Calculator calculatorProxy = ProxyFactory.createProxy(Calculator.class, calculator);

        try {
            // Вызываем методы через прокси
            int sum = calculatorProxy.add(5, 3);
            int diff = calculatorProxy.subtract(10, 4);
            int product = calculatorProxy.multiply(6, 7);
            double quotient = calculatorProxy.divide(20, 4);
            double error = calculatorProxy.divide(10, 0); // Будет выброшено исключение
        } catch (Exception e) {
            System.out.println("Произошло исключение: " + e.getMessage());
        }
    }
}
