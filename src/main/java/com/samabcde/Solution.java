package com.samabcde;

import java.util.List;

public class Solution {
    private final List<Placement> placements;
    private final Grid grid;

    public Solution(List<Placement> placements, Grid grid) {
        this.placements = placements;
        this.grid = grid;
    }
//    public add()

    public String toHumanReadable() {
        Dimension dimension = grid.getDimension();
        char[][] placedGrid = new char[dimension.height() * 3][dimension.width() * 3];
        for (int i = 0; i < placedGrid.length; i++) {
            for (int j = 0; j < placedGrid[0].length; j++) {
                placedGrid[i][j] = ' ';
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
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
