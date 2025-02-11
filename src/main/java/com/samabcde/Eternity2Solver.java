package com.samabcde;

import com.samabcde.state.PossiblePlacementFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Eternity2Solver {
    private static final Logger logger = LoggerFactory.getLogger(Eternity2Solver.class);
    private final Deque<Placement> solutions = new LinkedList<>();
    private final Eternity2Puzzle puzzle;
    private final List<Piece> pieces;

    public Eternity2Solver(Grid grid, List<Piece> pieces, List<Placement> givenPlacements) {
        PossiblePlacementFactory factory = new PossiblePlacementFactory(grid.getDimension());
        this.pieces = pieces;
        this.puzzle = new Eternity2Puzzle(grid, pieces, givenPlacements);
    }

    public Solution solve() {
        // initialize state
        // addGivenPlacements
        // solve
        puzzle.validate();
        long iterateCount = 0;
        while (true) {
            Piece piece = puzzle.getCurrent().next(pieces.stream().filter(p -> !solutions.stream().map(Placement::piece).collect(Collectors.toSet()).contains(p)).toList());
            Optional<Placement> next = puzzle.getCurrent().nextPossible(piece);
            if (puzzle.getCurrent().isSolvable() && next.isPresent()) {
                puzzle.addPlacement(next.get());
                solutions.push(next.get());
                logger.trace("add next:" + next.get());
                if (isSolved()) {
                    return new Solution(new ArrayList<>(solutions), puzzle.getGrid(), iterateCount);
                }
            } else {
                if (solutions.isEmpty()) {
                    return new Solution(new ArrayList<>(solutions), puzzle.getGrid(), iterateCount);
                } else {
                    var last = solutions.poll();
                    logger.trace("remove last:" + last);
                    puzzle.removePlacement(piece);
                }
            }
            iterateCount++;
            if (iterateCount % 10000 == 0) {
                logger.info(("iterate count:" + iterateCount + " solution size:" + solutions.size()));
            }
        }
    }

    public boolean isSolved() {
        return solutions.size() == pieces.size();
    }
}
