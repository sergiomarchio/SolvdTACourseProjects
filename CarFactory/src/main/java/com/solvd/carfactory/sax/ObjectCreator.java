package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ObjectCreator{
    private final static Logger LOGGER = Logger.getLogger(ObjectCreator.class);

    private static Object getParameter(Class<?> paramType, Object value){
        switch (paramType.getName()){
            case "long":
                return Long.parseLong(String.valueOf(value));
            default:
                throw new IllegalArgumentException("Unknown parameter type: " + paramType.getName());
        }
    }

    public static void populateObject(Object obj, String field, Object value){
        String setter = "set" + StringUtils.snakeToCamel(field);

        Method setValue = Arrays.stream(obj.getClass().getMethods())
                                .filter(m -> m.getName().equals(setter))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("No method with name " + setter
                                + "in " + obj.getClass()));

        // A String value corresponds to a non complex type
        String valueTypeName = value.getClass().getSimpleName();
        Class<?> fieldType = setValue.getParameterTypes()[0];

        Object newValue;
        if (fieldType.getSimpleName().equals(valueTypeName)){
            newValue = value;
        } else {
            newValue = getParameter(fieldType, value);
        }

        try {
            setValue.invoke(obj, newValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error invoking method " + setter + "\n" + e);
        }
    }
}
