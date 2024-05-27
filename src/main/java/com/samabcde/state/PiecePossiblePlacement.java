package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;

import java.util.List;

/**
 * Possible placement of each piece
 */
public class PiecePossiblePlacement {
    private final Piece piece;
    private final List<Placement> possiblePlacements;

    public PiecePossiblePlacement(Piece piece, List<Placement> possiblePlacements) {
        // corner piece 4 * 4
        // edge piece 14 * 4
        // inner piece 14 * 14 * 4 = 784
        // 784^(14*14)
        this.piece = piece;
        this.possiblePlacements = possiblePlacements;
    }
}
