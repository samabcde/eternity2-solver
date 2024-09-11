package com.samabcde;

public record Piece(int id, Color top, Color right, Color bottom, Color left) {
    public Piece(int id, Color top, Color right, Color bottom, Color left) {
        this.id = id;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public PositionType getPieceType() {
        if ((this.top.isBorder() && this.right.isBorder())
                || (this.right.isBorder() && this.bottom.isBorder())
                || (this.bottom.isBorder() && this.left.isBorder())
                || (this.left.isBorder() && this.top.isBorder())
        ) {
            return PositionType.CORNER;
        }
        if (this.top.isBorder()
                || this.right.isBorder()
                || this.bottom.isBorder()
                || this.left.isBorder()
        ) {
            return PositionType.SIDE;
        }
        return PositionType.INTERIOR;
    }

    public String toHumanReadable() {
        return """
                ╲%s╱
                %s╳%s
                ╱%s╲
                """.formatted(top, left, right, bottom);
    }

    public Color top(Rotation rotation) {
        return switch (rotation) {
            case _0 -> top();
            case _90 -> right();
            case _180 -> bottom();
            case _270 -> left();
        };
    }

    public Color right(Rotation rotation) {
        return switch (rotation) {
            case _0 -> right();
            case _90 -> bottom();
            case _180 -> left();
            case _270 -> top();
        };
    }

    public Color bottom(Rotation rotation) {
        return switch (rotation) {
            case _0 -> bottom();
            case _90 -> left();
            case _180 -> top();
            case _270 -> right();
        };
    }

    public Color left(Rotation rotation) {
        return switch (rotation) {
            case _0 -> left();
            case _90 -> top();
            case _180 -> right();
            case _270 -> bottom();
        };
    }
}
