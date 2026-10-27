package service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.annotation.Id;

public class Repository<T> {
    private HashMap<String, T> store = new HashMap<>();
    
    public T save(T entity) {
        if (entity == null) {
            return null;
        }
        String id = getId(entity);
        if (id != null) {
            store.put(id, entity);
        }
        return entity;
    }
    
    public T findById(String id) {
        return store.get(id);
    }
    
    public ArrayList<T> findAll() {
        return new ArrayList<T>(store.values());
    }
    
    public boolean delete(String id) {
        return store.remove(id) != null;
    }
    
    @SafeVarargs
    public final void saveAll(T... items) {
        if (items != null) {
            for (T item : items) {
                save(item);
            }
        }
    }
    
    private String getId(T entity) {
        Class<?> clazz = entity.getClass();
        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value != null) {
                            return value.toString();
                        }
                    } catch (Exception e) {
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
    
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (N value : values) {
            if (value != null) {
                sum += value.doubleValue();
            }
        }
        return sum / values.length;
    }
}
