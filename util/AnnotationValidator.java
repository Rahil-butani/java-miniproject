package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.MaxLength;
import model.annotation.Positive;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        if (obj == null) {
            return new String[0];
        }
        List<String> errors = new ArrayList<String>();
        Class<?> clazz = obj.getClass();
        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Positive.class)) {
                        Object value = field.get(obj);
                        if (value instanceof Number) {
                            long num = ((Number) value).longValue();
                            if (num <= 0) {
                                Positive positive = field.getAnnotation(Positive.class);
                                errors.add(field.getName() + ": " + positive.message());
                            }
                        }
                    }
                    if (field.isAnnotationPresent(MaxLength.class)) {
                        Object value = field.get(obj);
                        if (value instanceof String) {
                            String str = (String) value;
                            MaxLength maxLength = field.getAnnotation(MaxLength.class);
                            if (str.length() > maxLength.value()) {
                                errors.add(field.getName() + ": exceeds max length " + maxLength.value());
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
            clazz = clazz.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}
