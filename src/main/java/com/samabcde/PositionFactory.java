package com.samabcde;

public class PositionFactory {
    private final Dimension dimension;

    public PositionFactory(Dimension dimension) {
        this.dimension = dimension;
    }

    public Position createPosition(int x, int y) {
        return new Position(y * dimension.width() + x, x, y, getPositionType(x,y));
    }

    public Position createPosition(String value) {
        if (value.split(",").length != 2) {
            throw new IllegalArgumentException("invalid value: [%s]".formatted(value));
        }
        int x = Integer.parseInt(value.split(",")[0]);
        int y = Integer.parseInt(value.split(",")[1]);
        return new Position(y * dimension.width() + x, x, y,getPositionType(x,y));
    }

    private PositionType getPositionType(int x, int y) {
        if (x == 0 && y == 0) {
            return PositionType.UPPER_LEFT_CORNER;
        }
        if (x == 0 && y == dimension.width() - 1) {
            return PositionType.UPPER_RIGHT_CORNER;
        }
        if (x == dimension.height() - 1 && y == 0) {
            return PositionType.LOWER_LEFT_CORNER;
        }
        if (x == dimension.height() - 1 && y == dimension.width() - 1) {
            return PositionType.LOWER_RIGHT_CORNER;
        }
        if (x == 0) {
            return PositionType.UPPER_SIDE;
        }
        if (x == dimension.height() - 1) {
            return PositionType.LOWER_SIDE;
        }
        if (y == 0) {
            return PositionType.LEFT_SIDE;
        }
        if (y == dimension.width() - 1) {
            return PositionType.RIGHT_SIDE;
        }
        return PositionType.INTERIOR;
    }
}
