package businessLayer;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class DynamicBeanUtils {
    public static <T> T getPropertyValue(Object instance, PropertyDescriptor descriptor) {
        Method m = descriptor.getReadMethod();
        try {
            // System.out.println("d:" + descriptor);
            // System.out.println("i:" + instance.toString());
            // System.out.println("m:" + m);
            Object result = m.invoke(instance);
            return (T) result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}