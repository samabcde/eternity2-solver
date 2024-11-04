package com.samabcde;

public record Position(int id, int x, int y, PositionType positionType) {
    boolean isBelow(Position other) {
        return this.x == other.x && this.y == other.y + 1;
    }

    boolean isAbove(Position other) {
        return this.x == other.x && this.y == other.y - 1;
    }

    boolean isLeft(Position other) {
        return this.x == other.x - 1 && this.y == other.y;
    }

    boolean isRight(Position other) {
        return this.x == other.x + 1 && this.y == other.y;
    }
}
