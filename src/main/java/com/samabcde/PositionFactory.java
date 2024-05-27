package com.samabcde;

public class PositionFactory {
    private final Dimension dimension;

    public PositionFactory(Dimension dimension) {
        this.dimension = dimension;
    }

    public Position createPosition(int x, int y) {
        return new Position(y * dimension.width() + x, x, y);
    }

    public Position createPosition(String value) {
        if (value.split(",").length != 2) {
            throw new IllegalArgumentException("invalid value: [%s]".formatted(value));
        }
        int x = Integer.parseInt(value.split(",")[0]);
        int y = Integer.parseInt(value.split(",")[1]);
        return new Position(y * dimension.width() + x, x, y);
    }
}
