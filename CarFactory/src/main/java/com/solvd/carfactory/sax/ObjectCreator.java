package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;

public class ObjectCreator {
    private final static Logger LOGGER = Logger.getLogger(ObjectCreator.class);

    private static Object getParameter(Class<?> paramType, String value) {
        switch (paramType.getName()) {
            case "double":
                return Double.parseDouble(value);
            case "long":
                return Long.parseLong(value);
            case "java.time.Year":
                return Year.parse(value);
            default:
                throw new IllegalArgumentException("Unknown parameter type: " + paramType.getName()
                        + " value: " + value);
        }
    }

    public static void populateObject(Object obj, String field, Object value) {
        String setter = "set" + StringUtils.snakeToCamel(field);

        Method setValue = Arrays.stream(obj.getClass().getMethods())
                .filter(m -> m.getName().equals(setter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No method with name " + setter
                        + " in " + obj.getClass()));

        // A String value corresponds to a non complex type
        String valueTypeName = value.getClass().getSimpleName();
        Class<?> fieldType = setValue.getParameterTypes()[0];

        Object newValue;
        if (fieldType.getSimpleName().equals(valueTypeName)
                || Collection.class.isAssignableFrom(fieldType)) {
            newValue = value;
        } else {
            newValue = getParameter(fieldType, String.valueOf(value));
        }

        try {
            setValue.invoke(obj, newValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error invoking method " + setter + "\n" + e);
        }
    }
}
