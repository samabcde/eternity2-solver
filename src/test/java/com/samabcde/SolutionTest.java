package com.samabcde;

import com.samabcde.puzzles.Puzzle;
import org.junit.jupiter.api.Test;

import java.util.List;

class SolutionTest {
    @Test
    public void test1() {
        Eternity2Setup setup = new Eternity2Setup(Puzzle.D16);
        List<Piece> pieces = setup.pieces;
        Solution solution = new Solution(setup.dimension.allPositions()
                .map(p -> new Placement(pieces.get(p.y() * 16 + p.x()), p, Rotation._0)).toList(),
                new Grid(setup.dimension), 0);
        System.out.println(solution.toHumanReadable());
    }
}