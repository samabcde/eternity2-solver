package com.samabcde;

import com.samabcde.puzzles.Puzzle;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SolverApplication {
    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        Eternity2Setup setup = new Eternity2Setup(Puzzle.D7);
        Eternity2Solver solver = new Eternity2Solver(new Grid(setup.dimension), setup.pieces, List.of());
        Solution solution = solver.solve();
        System.out.println("puzzle is solved: " + solution.isSolved() + ", iterate count:" + solution.getIterateCount());
        System.out.println(solution.toHumanReadable());
    }
}
