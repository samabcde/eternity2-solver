package com.samabcde;

import java.util.LinkedList;
import java.util.List;

public class Eternity2Solver {
    private final Grid grid;
    private final List<Piece> pieces;
    private final List<Placement> givenPlacements;
    private final List<Placement> solutions = new LinkedList<>();
//    private final boolean[][] filterPlacement;

    public Eternity2Solver(Grid grid, List<Piece> pieces, List<Placement> givenPlacements) {
        this.grid = grid;
        this.pieces = pieces;
        this.givenPlacements = givenPlacements;
    }

    public Solution solve() {
        validateGrid();
        validateCount();
        validateCorner();
        validateSide();
        validateInterior();
        // initialize state
        // addGivenPlacements
        // solve
        return null;
    }

    private void validateGrid() {
        if (grid.getDimension().width() < 2) {
            throw new IllegalArgumentException("grid width: " + grid.getDimension().width() + " should be greater than or equals to 2");
        }
        if (grid.getDimension().height() < 2) {
            throw new IllegalArgumentException("grid height: " + grid.getDimension().height() + " should be greater than or equals to 2");
        }
    }

    private void validateCount() {
        if (pieces.size() != grid.getSize()) {
            throw new IllegalArgumentException("no. of pieces: " + pieces.size() + " should match grid size: " + grid.getSize());
        }
    }

    private void validateCorner() {
        long cornerCount = pieces.stream().filter(piece -> piece.getPieceType() == PieceType.CORNER).count();
        int expectedCornerCount = 4;
        if (cornerCount != expectedCornerCount) {
            throw new IllegalArgumentException("no. of corner pieces: " + cornerCount + " should be " + expectedCornerCount);
        }
    }

    private void validateSide() {
        long sideCount = pieces.stream().filter(piece -> piece.getPieceType() == PieceType.SIDE).count();
        int expectedSideCount = 2 * (this.grid.getDimension().width() - 2) + 2 * (this.grid.getDimension().height() - 2);
        if (sideCount != expectedSideCount) {
            throw new IllegalArgumentException("no. of side pieces: " + sideCount + " should be " + expectedSideCount);
        }
    }

    private void validateInterior() {
        long interiorCount = pieces.stream().filter(piece -> piece.getPieceType() == PieceType.INTERIOR).count();
        int expectedInteriorCount = (this.grid.getDimension().width() - 2) * (this.grid.getDimension().height() - 2);
        if (interiorCount != expectedInteriorCount) {
            throw new IllegalArgumentException("no. of interior pieces: " + interiorCount + " should be " + expectedInteriorCount);
        }
    }
}
