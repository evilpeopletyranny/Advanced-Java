package annotation2_runtime.code;

public class Client {
    @Inject
    private Service service;

    public void doWork() {
        service.execute();
    }
}
