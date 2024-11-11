package reflect2_dynamic.code;

public class Main {
    public static void main(String[] args) {
        try {
            ServiceB serviceB = new ServiceB();
            DIContainer.initialize(serviceB);
            serviceB.performAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
