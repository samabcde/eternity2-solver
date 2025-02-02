package com.samabcde;

import com.samabcde.state.*;

import java.util.*;
import java.util.stream.Collectors;

public class Eternity2Puzzle {
    public Grid getGrid() {
        return grid;
    }

    private final Grid grid;
    private final List<Piece> pieces;
    private final List<Placement> givenPlacements;
    private final List<PiecePlacementIntersection> piecePlacementIntersections;
    private final Deque<PuzzleState> states = new ArrayDeque<>();
    private PuzzleState current;

    public Eternity2Puzzle(Grid grid, List<Piece> pieces, List<Placement> givenPlacements) {
        this.grid = grid;
        this.pieces = pieces;
        this.givenPlacements = givenPlacements;
        PossiblePlacementFactory factory = new PossiblePlacementFactory(grid.getDimension());
        var piecePossiblePlacements = this.pieces.stream().map(factory::create).toList();
        this.piecePlacementIntersections = intersect(piecePossiblePlacements);
        var positionPossiblePlacement = grid.getDimension().allPositions().map(
                position -> new PositionPossiblePlacement(position, piecePossiblePlacements.stream().flatMap(p -> p.possiblePlacements().stream())
                        .filter(placement -> placement.position().equals(position))
                        .toList()
                )).toList();
        PuzzleState e = new PuzzleState(
                piecePossiblePlacements,
                positionPossiblePlacement,
                pieces.stream().map(p -> new PieceTriedPlacement(p, Set.of())).toList()
        );
        current = e;
    }

    private static List<PiecePlacementIntersection> intersect(List<PiecePossiblePlacement> piecePossiblePlacements) {
        return piecePossiblePlacements.stream().map(ppp ->
                new PiecePlacementIntersection(ppp.piece(), ppp.possiblePlacements().stream().map(
                        pp -> new PlacementIntersection(pp,
                                piecePossiblePlacements.stream()
                                        .filter(opp -> opp != ppp)
                                        .flatMap(opp -> opp.possiblePlacements().stream().filter(op -> isIntersect(op, pp))
                                        ).toList()
                        )
                ).toList()
                )
        ).toList();
    }

    private static boolean isIntersect(Placement placement1, Placement placement2) {
        if (placement1.position().equals(placement2.position())) {
            return true;
        }
        if (placement1.isLeft(placement2)) {
            return !placement1.right().equals(placement2.left());
        } else if (placement1.isRight(placement2)) {
            return !placement1.left().equals(placement2.right());
        } else if (placement1.isAbove(placement2)) {
            return !placement1.bottom().equals(placement2.top());
        } else if (placement1.isBelow(placement2)) {
            return !placement1.top().equals(placement2.bottom());
        } else {
            return false;
        }
    }

    public void validate() {
        validateGrid();
        validateCount();
        validateCorner();
        validateInterior();
        validateSide();
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

    public void addPlacement(Placement placement) {
        // add not possible
        // push new state
        var removed = piecePlacementIntersections.stream().filter(p -> p.piece().equals(placement.piece()))
                .flatMap(p -> p.placementIntersections().stream())
                .filter(p -> p.placement().equals(placement))
                .flatMap(p -> p.intersections().stream()).collect(Collectors.toSet());
        PuzzleState afterRemove = this.current.tryPlacement(placement, removed);
        states.push(this.current);
        this.current = afterRemove;
    }

    public void removePlacement(Piece lastTried) {
        Optional<Placement> next = current.nextPossible(lastTried);
        var temp = states.pop();
        if (next.isEmpty()) {
            this.current = new PuzzleState(temp.piecePossiblePlacements(), temp.positionPossiblePlacements(), this.current.pieceTriedPlacements()).clearTried(lastTried);
        } else {
            this.current = new PuzzleState(temp.piecePossiblePlacements(), temp.positionPossiblePlacements(), this.current.pieceTriedPlacements());
        }
    }

    public PuzzleState getCurrent() {
        return current;
    }
}
