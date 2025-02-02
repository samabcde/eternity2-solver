package com.samabcde;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
    private static final Logger logger = LoggerFactory.getLogger(Converter.class);

    public static void main(String[] args) {
        List<String> unique = (getStream().flatMap(s -> Arrays.stream(s.split(" "))).distinct().sorted().toList());
        Map<String, Character> mapping = new LinkedHashMap<>();
        for (int i = 0; i < unique.size(); i++) {
            mapping.put(unique.get(i), (char) ('a' + i));
        }
        logger.info(mapping.toString());
        List<String> mapped = getStream().map(s -> Arrays.stream(s.split(" ")).map(e -> String.valueOf(mapping.get(e))).collect(Collectors.joining())).toList();
        mapped.forEach(s -> logger.info("\"{}\",", s));
    }

    static String s5_5 = """
            0 1 29 0,0 23 37 1,0 33 30 23,0 37 2 33,0 0 32 37,
            29 30 30 0,37 37 23 30,30 37 38 37,2 37 33 37,32 0 29 37,
            30 32 37 0,23 29 29 32,38 37 2 29,33 32 23 37,29 0 33 32,
            37 30 1 0,29 33 32 30,2 29 29 33,23 37 1 29,33 0 30 37,
            1 2 0 0,32 5 0 2,29 5 0 5,1 30 0 5,30 0 0 30
            """;
    static String s6_6 = """
            0 29 10 0,0 30 23 29,0 7 10 30,0 29 23 7,0 33 33 29,0 0 34 33,
            10 34 30 0,23 29 25 34,10 3 17 29,23 33 17 3,33 17 10 33,34 0 30 17,
            30 3 7 0,25 29 7 3,17 33 29 29,17 7 17 33,10 34 10 7,30 0 30 34,
            7 33 23 0,7 25 29 33,29 17 34 25,17 33 34 17,10 3 10 33,30 0 30 3,
            23 25 7 0,29 25 7 25,34 34 29 25,34 7 29 34,10 23 10 7,30 0 23 23,
            7 33 0 0,7 34 0 33,29 10 0 34,29 30 0 10,10 29 0 30,23 0 0 29
            """;
    static String s7_7 = """
            0 17 5 0,0 29 17 17,0 2 6 29,0 26 26 2,0 17 15 26,0 17 5 17,0 0 2 17,
            5 5 7 0,17 35 5 5,6 15 16 35,26 7 6 15,15 5 17 7,5 35 16 5,2 0 2 35,
            7 29 7 0,5 2 29 29,16 26 35 2,6 26 35 26,17 7 17 26,16 5 7 7,2 0 29 5,
            7 29 26 0,29 16 16 29,35 29 15 16,35 7 26 29,17 26 16 7,7 29 29 26,29 0 16 29,
            26 17 5 0,16 16 29 17,15 6 7 16,26 35 15 6,16 7 6 35,29 7 35 7,16 0 15 7,
            5 6 15 0,29 17 17 6,7 5 2 17,15 7 5 5,6 15 2 7,35 6 2 15,15 0 6 6,
            15 16 0 0,17 26 0 16,2 17 0 26,5 35 0 17,2 16 0 35,2 26 0 16,6 0 0 26,
            """;
    static String s8_8 = """
            13 32 10 13,31 0 10 36,22 31 10 10,1 25 36 36,10 0 0 25,10 31 36 33,31 10 13 0,2 33 39 10,
            31 37 32 22,32 32 1 13,31 31 1 0,1 10 13 1,2 0 36 13,33 0 1 2,39 39 36 25,32 13 36 31,
            33 31 32 31,32 39 22 0,32 1 0 33,33 22 1 13,22 13 36 1,32 39 22 36,2 22 13 13,31 31 1 10,
            10 10 2 37,10 22 37 22,37 32 0 0,31 0 10 31,0 13 1 0,36 31 13 31,31 0 31 31,10 33 31 31,
            32 10 37 2,32 0 33 10,31 25 37 0,1 31 36 39,10 36 0 22,2 39 13 0,13 33 0 22,32 10 10 31,
            32 25 22 10,36 10 22 2,22 36 37 32,36 2 37 36,36 37 2 2,1 36 31 0,32 1 1 39,1 22 0 1,
            33 13 1 10,36 31 36 36,0 32 32 31,31 39 13 22,1 0 0 22,0 2 13 33,33 22 0 37,2 36 13 22,
            2 2 0 33,36 31 2 22,33 31 36 39,0 1 37 2,36 25 0 2,36 36 31 10,0 13 36 10,0 31 10 37,
            """;

    private static Stream<String> getStream() {
        return Arrays.stream(s8_8.replace("\n", "").split(","));
    }
}
