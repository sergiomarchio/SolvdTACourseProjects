package com.solvd.uniquewordcount.runner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    public static final void main(String[] args) throws IOException{
        String text = FileUtils.readFileToString(new File("src/main/resources/input.txt"), "UTF-8");

        Map<String, Long> uniqueCount = Arrays.stream(StringUtils.split(text, " ,.;:!?-_()/\"\r\n\uFEFF"))
                .map(StringUtils::lowerCase)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        FileUtils.writeStringToFile(new File("src/main/resources/output.txt"), uniqueCount.toString(), "UTF-8");
    }
}
