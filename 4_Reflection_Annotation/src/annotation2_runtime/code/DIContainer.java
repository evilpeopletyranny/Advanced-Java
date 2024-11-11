package annotation2_runtime.code;

import java.lang.reflect.Field;

public class DIContainer {

    public <T> T getInstance(Class<T> clazz) throws Exception {
        return createInstance(clazz);
    }

    private <T> T createInstance(Class<T> clazz) throws Exception {
        // Создаем новый экземпляр класса
        T instance = clazz.getDeclaredConstructor().newInstance();

        // Инъекция зависимостей в поля, помеченные @Inject
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                // Получаем тип поля
                Class<?> fieldType = field.getType();

                // Рекурсивно создаем экземпляр зависимости
                Object dependency = createInstance(fieldType);

                // Устанавливаем значение поля
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }
        return instance;
    }
}

