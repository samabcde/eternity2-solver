package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;

import java.util.List;

/**
 * Possible placement of each piece
 */
public record PiecePossiblePlacement(Piece piece, List<Placement> possiblePlacements) {
    // corner piece 4 * 4
    // edge piece 14 * 4
    // inner piece 14 * 14 * 4 = 784
    // 784^(14*14)
}
