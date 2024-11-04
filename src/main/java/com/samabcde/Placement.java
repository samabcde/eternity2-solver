package com.samabcde;

public record Placement(Piece piece, Position position, Rotation rotation) {

    public String toHumanReadableString() {
        return position.toString() +
                """
                        ╲%s╱
                        %s╳%s
                        ╱%s╲
                        """.formatted(top(), left(), right(), bottom());
    }

    boolean isBelow(Placement other) {
        return this.position.isBelow(other.position);
    }

    boolean isAbove(Placement other) {
        return this.position.isAbove(other.position);
    }

    boolean isLeft(Placement other) {
        return this.position.isLeft(other.position);
    }

    boolean isRight(Placement other) {
        return this.position.isRight(other.position);
    }

    Color left() {
        return piece.left(rotation);
    }

    Color right() {
        return piece.right(rotation);
    }

    Color top() {
        return piece.top(rotation);
    }

    Color bottom() {
        return piece.bottom(rotation);
    }
}
