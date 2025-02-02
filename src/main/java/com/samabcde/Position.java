package com.samabcde;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
