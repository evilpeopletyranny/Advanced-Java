package reflect2_dynamic.code;

import java.lang.reflect.Field;

public class DIContainer {
    public static void initialize(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> fieldType = field.getType();
                System.out.println(fieldType);
                Object dependency = fieldType.getDeclaredConstructor().newInstance();
                field.setAccessible(true);
                field.set(object, dependency);
            }
        }
    }
}

