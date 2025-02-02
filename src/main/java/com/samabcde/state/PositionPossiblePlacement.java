package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;
import com.samabcde.Position;

import java.util.List;
import java.util.Set;

/**
 * Possible placement of each position
 */
public record PositionPossiblePlacement(Position position, List<Placement> possiblePlacements) {
    public PositionPossiblePlacement excludeIntersect(
            Placement placement, Set<Placement> intersect
    ) {
        return new PositionPossiblePlacement(
                position(),
                possiblePlacements().stream().filter(p ->
                        !(intersect.contains(p))
                                && (!p.piece().equals(placement.piece()))
                ).toList());
    }

    public PositionPossiblePlacement excludeOnePiecePositions(List<PositionPossiblePlacement> onePiecePositions, Set<Piece> pieces) {
        if (onePiecePositions.contains(this)) {
            return this;
        }
        return new PositionPossiblePlacement(
                position(),
                possiblePlacements().stream().filter(p -> !pieces.contains(p.piece())).toList());
    }
}
