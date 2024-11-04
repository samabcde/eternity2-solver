package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;

import java.util.Set;

public record PieceTriedPlacement(Piece piece, Set<Placement> triedPlacements) {
}
