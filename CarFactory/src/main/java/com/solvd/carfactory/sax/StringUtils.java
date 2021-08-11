package com.solvd.carfactory.sax;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringUtils {
    public static String camelToSnake(String camel) {
        if (camel.length() <= 1){
            return camel.toLowerCase();
        }

        return camel.substring(0, 1).toLowerCase(Locale.ROOT)
                + camel.substring(1).codePoints()
                       .mapToObj(c -> String.valueOf((char) c))
                       .map(c -> c.equals(c.toUpperCase()) ? "_" + c.toLowerCase() : c)
                       .collect(Collectors.joining());
    }

    public static String snakeToCamel (String snake){
        return Arrays.stream(snake.split("_"))
                .map(s -> s.substring(0,1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());
    }
}
