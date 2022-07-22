package helpers.factories;

import helpers.config.TestConfiguration;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class ObjectProvider {

    @SuppressWarnings("unchecked")
    public static <T> T instance(final Class<T> objectClass, final Object... args) {
        Class<T> instanceClass = objectClass;
        String instanceClassName = objectClass.getName();
        try {
            if (TestConfiguration.isIOS()) {
                instanceClassName = instanceClassName.replaceFirst(".android.", String.format(".%s.", "ios"))
                    + "IOS";
            }
            instanceClass = (Class<T>) Class.forName(instanceClassName);
        } catch (ClassNotFoundException e) {
        }
        try {
            final T instanceObject = ConstructorUtils.invokeConstructor(instanceClass, args);
            if (instanceObject == null) {
                throw new NoSuchMethodException("Can not find matching accessible constructor");
            }
            return instanceObject;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Can not create instance of class " + instanceClassName, e.getCause());
        }
    }

}
