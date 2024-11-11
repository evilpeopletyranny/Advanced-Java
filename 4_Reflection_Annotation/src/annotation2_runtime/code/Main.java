package annotation2_runtime.code;

public class Main {
    public static void main(String[] args) {
        try {
            DIContainer container = new DIContainer();
            Client client = container.getInstance(Client.class);
            client.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
