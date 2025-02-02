package com.samabcde;

import com.samabcde.puzzles.Puzzle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SolverApplication {
    private static final Logger logger = LoggerFactory.getLogger(SolverApplication.class);

    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        Eternity2Setup setup = new Eternity2Setup(Puzzle.D8);
        Eternity2Solver solver = new Eternity2Solver(new Grid(setup.dimension), setup.pieces, List.of());
        Solution solution = solver.solve();
        logger.info("puzzle is solved: " + solution.isSolved() + ", iterate count:" + solution.getIterateCount());
        logger.info("\n" + solution.toHumanReadable());
    }
}
