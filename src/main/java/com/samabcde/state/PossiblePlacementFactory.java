package com.samabcde.state;

import com.samabcde.Dimension;
import com.samabcde.Piece;
import com.samabcde.Placement;
import com.samabcde.Rotation;

import java.util.Arrays;


public class PossiblePlacementFactory {
    private final Dimension dimension;

    public PossiblePlacementFactory(Dimension dimension) {
        this.dimension = dimension;
    }

    public PiecePossiblePlacement create(Piece piece) {
        return switch (piece.getPieceType()) {
            case CORNER -> createCorner(piece);
            case SIDE -> createSide(piece);
            case INTERIOR -> createInterior(piece);
        };
    }

    private PiecePossiblePlacement createInterior(Piece piece) {
        return new PiecePossiblePlacement(piece, dimension.interiorPositions().flatMap(p ->
                (switch (p.positionType()) {
                    case INTERIOR -> Arrays.stream(Rotation.values());
                    case UPPER_LEFT_CORNER, UPPER_RIGHT_CORNER, LOWER_LEFT_CORNER, LOWER_RIGHT_CORNER, UPPER_SIDE,
                         LEFT_SIDE, RIGHT_SIDE, LOWER_SIDE ->
                            throw new IllegalArgumentException(p.positionType().name() + " is not expected");
                }).map(r -> new Placement(piece, p, r))).toList());
    }

    private PiecePossiblePlacement createCorner(Piece piece) {
        return new PiecePossiblePlacement(piece, dimension.cornerPositions().flatMap(p ->
                (switch (p.positionType()) {
                    case UPPER_LEFT_CORNER ->
                            Arrays.stream(Rotation.values()).filter(r -> piece.top(r).isBorder() && piece.left(r).isBorder());
                    case UPPER_RIGHT_CORNER ->
                            Arrays.stream(Rotation.values()).filter(r -> piece.top(r).isBorder() && piece.right(r).isBorder());
                    case LOWER_LEFT_CORNER ->
                            Arrays.stream(Rotation.values()).filter(r -> piece.bottom(r).isBorder() && piece.left(r).isBorder());
                    case LOWER_RIGHT_CORNER ->
                            Arrays.stream(Rotation.values()).filter(r -> piece.bottom(r).isBorder() && piece.right(r).isBorder());
                    case UPPER_SIDE, LEFT_SIDE, RIGHT_SIDE, LOWER_SIDE, INTERIOR ->
                            throw new IllegalArgumentException(p.positionType().name() + " is not expected");
                }).map(r -> new Placement(piece, p, r))).toList());
    }

    private PiecePossiblePlacement createSide(Piece piece) {
        return new PiecePossiblePlacement(piece, dimension.sidePositions().flatMap(p ->
                (switch (p.positionType()) {
                    case UPPER_SIDE -> Arrays.stream(Rotation.values()).filter(r -> piece.top(r).isBorder());
                    case LOWER_SIDE -> Arrays.stream(Rotation.values()).filter(r -> piece.bottom(r).isBorder());
                    case LEFT_SIDE -> Arrays.stream(Rotation.values()).filter(r -> piece.left(r).isBorder());
                    case RIGHT_SIDE -> Arrays.stream(Rotation.values()).filter(r -> piece.right(r).isBorder());
                    case UPPER_LEFT_CORNER, UPPER_RIGHT_CORNER, LOWER_LEFT_CORNER, LOWER_RIGHT_CORNER,
                         INTERIOR -> throw new IllegalArgumentException(p.positionType().name() + " is not expected");
                }).map(r -> new Placement(piece, p, r))).toList());
    }
}
