package com.samabcde;

import java.util.List;

public class Solution {
    private final List<Placement> placements;
    private final Grid grid;
    private final boolean isSolved;
    private final long iterateCount;

    public Solution(List<Placement> placements, Grid grid, long iterateCount) {
        this.placements = placements;
        this.grid = grid;
        this.isSolved = placements.size() == grid.getSize();
        this.iterateCount = iterateCount;
    }
//    public add()

    public String toHumanReadable() {
        Dimension dimension = grid.getDimension();
        char[][] placedGrid = new char[dimension.height() * 3][dimension.width() * 3];
        for (int i = 0; i < placedGrid.length; i++) {
            for (int j = 0; j < placedGrid[0].length; j++) {
                if (i % 3 == 1 && j % 3 == 1) {
                    placedGrid[i][j] = '╳';
                } else if (i % 3 == 0) {
                    placedGrid[i][j] = (j % 3 == 0 ? '╲' : j % 3 == 2 ? '╱' : ' ');
                } else if (i % 3 == 2) {
                    placedGrid[i][j] = (j % 3 == 2 ? '╲' : j % 3 == 0 ? '╱' : ' ');
                } else {
                    placedGrid[i][j] = ' ';
                }
            }
        }
        // assume upper left corner is 0,0
        for (Placement placement : placements) {
            int x = 3 * placement.position().x() + 1;
            int y = 3 * placement.position().y() + 1;
            // placedGrid
            char top = placement.piece().top(placement.rotation()).code();
            char right = placement.piece().right(placement.rotation()).code();
            char bottom = placement.piece().bottom(placement.rotation()).code();
            char left = placement.piece().left(placement.rotation()).code();
            placedGrid[y - 1][x] = top;
            placedGrid[y][x + 1] = right;
            placedGrid[y + 1][x] = bottom;
            placedGrid[y][x - 1] = left;
        }
        StringBuilder builder = new StringBuilder();
        for (char[] row : placedGrid) {
            builder.append(row);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public boolean isSolved() {
        return isSolved;
    }

    public long getIterateCount() {
        return iterateCount;
    }
}
