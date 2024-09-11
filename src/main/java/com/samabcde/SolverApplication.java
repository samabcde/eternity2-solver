package com.samabcde;

import com.samabcde.puzzles.Puzzle;

import java.util.List;

public class SolverApplication {
    public static void main(String[] args) {
        Eternity2Setup setup = new Eternity2Setup(Puzzle.D2);
        Eternity2Solver solver = new Eternity2Solver(new Grid(setup.dimension), setup.pieces, List.of());
        Solution solution = solver.solve();

    }
}
