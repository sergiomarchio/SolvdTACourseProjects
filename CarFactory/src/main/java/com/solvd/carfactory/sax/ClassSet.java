package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassSet {
    private final static Logger LOGGER = Logger.getLogger(ClassSet.class);

    private final static String PKG_NAME = "com.solvd.carfactory.models";

    public static Map<String, Class<?>> getClassTags(){
        return getClasses().stream()
                .collect(Collectors.toMap(c -> StringUtils.camelToSnake(c.getSimpleName()), c -> c));
    }

    public static Set<Class<?>> getClasses() {
        return getClasses(PKG_NAME);
    }

    public static Set<Class<?>> getClasses(String pkgName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(pkgName.replace(".", "/"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Set<Class<?>> classes = new HashSet<>();

        reader.lines()
                .forEach(x -> {
                    String fullPath = pkgName + "." + x;
                    if (x.endsWith(".class")) {
                        try {
                            classes.add(Class.forName(fullPath.replace(".class", "")));
                        } catch (ClassNotFoundException e) {
                            LOGGER.error("Error getting classes: " + e);
                        }
                    } else {
                        classes.addAll(getClasses(fullPath));
                    }
                });

        return classes;
    }

}
