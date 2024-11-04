package com.samabcde;

public enum PositionType {
    UPPER_LEFT_CORNER(true, false),
    UPPER_RIGHT_CORNER(true, false),
    LOWER_LEFT_CORNER(true, false),
    LOWER_RIGHT_CORNER(true, false),
    UPPER_SIDE(false, true),
    LEFT_SIDE(false, true),
    RIGHT_SIDE(false, true),
    LOWER_SIDE(false, true),
    INTERIOR(false, false);
    final boolean isCorner;
    final boolean isSide;
    final boolean isInterior;

    PositionType(boolean isCorner, boolean isSide) {
        this.isCorner = isCorner;
        this.isSide = isSide;
        this.isInterior = !isCorner && !isSide;
    }
}
