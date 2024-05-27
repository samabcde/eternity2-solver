package com.samabcde;

import org.junit.jupiter.api.Test;

import java.util.List;

class SolutionTest {
    @Test
    public void test1() {
        List<Piece> pieces = Eternity2Setup.pieces;
        Solution solution = new Solution(Eternity2Setup.dimension.allPositions()
                .map(p -> new Placement(pieces.get(p.y() * 16 + p.x()), p, Rotation._0)).toList(),
                new Grid(Eternity2Setup.dimension));
        System.out.println(solution.toHumanReadable());
    }
}