package reflect2_dynamic.code;

public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void performAction() {
        serviceA.doSomething();
    }
}
