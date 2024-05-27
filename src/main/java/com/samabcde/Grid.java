package com.samabcde;

public class Grid {
    public Dimension getDimension() {
        return dimension;
    }

    public int getSize() {
       return dimension.width() * dimension.height();
    }

    private final Dimension dimension;

    public Grid(Dimension dimension) {
        this.dimension = dimension;
    }
}
