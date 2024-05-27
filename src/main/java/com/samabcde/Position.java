package com.samabcde;

public record Position(int id, int x, int y) {
    public static Position parse(String value) {
        if (value.split(",").length != 2) {
            throw new IllegalArgumentException("invalid value: [%s]".formatted(value));
        }
        int x = Integer.parseInt(value.split(",")[0]);
        int y = Integer.parseInt(value.split(",")[1]);
        return new Position(0, x, y);
    }
}
