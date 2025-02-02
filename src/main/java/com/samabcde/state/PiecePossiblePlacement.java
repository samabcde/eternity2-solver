package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;

import java.util.List;
import java.util.Set;

/**
 * Possible placement of each piece
 */
public record PiecePossiblePlacement(Piece piece, List<Placement> possiblePlacements) {
    // corner piece 4 * 4
    // edge piece 14 * 4
    // inner piece 14 * 14 * 4 = 784
    // 784^(14*14)
    public PiecePossiblePlacement excludeIntersect(Set<Placement> intersect) {
        return new PiecePossiblePlacement(
                piece(),
                possiblePlacements().stream().filter(p -> !intersect.contains(p)
                ).toList()
        );
    }

    public PiecePossiblePlacement excludeOnePiecePositions(List<PositionPossiblePlacement> onePiecePositions, Set<Piece> pieces) {
        if (pieces.contains(this.piece())) {
            var position = onePiecePositions.stream().filter(
                    o -> o.possiblePlacements().stream().allMatch(p -> p.piece().equals(this.piece()))
            ).findFirst().orElseThrow().position();
            return new PiecePossiblePlacement(this.piece(),
                    this.possiblePlacements().stream().filter(
                            p -> p.position().equals(position)
                    ).toList());
        } else {
            return this;
        }
    }
}
