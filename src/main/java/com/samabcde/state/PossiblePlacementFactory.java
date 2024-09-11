package com.samabcde.state;

import com.samabcde.Dimension;
import com.samabcde.Piece;
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
            case SIDE -> null;
            case INTERIOR -> null;
        };
    }

    private PiecePossiblePlacement createCorner(Piece piece) {
//        dimension.cornerPositions().map(p-> Arrays.stream(Rotation.values()).filter())
        return null;
    }
}
