package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Placement;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record PuzzleState(List<PiecePossiblePlacement> piecePossiblePlacements,
                          List<PositionPossiblePlacement> positionPossiblePlacements,
                          List<PieceTriedPlacement> pieceTriedPlacements
) {
    public Piece next(List<Piece> remainings) {
        return remainings.stream().min(
                (p1, p2) ->
                {
                    int compare = Integer.compare(
                            piecePossiblePlacements.stream().filter(p -> p.piece().equals(p1)).findFirst().orElseThrow().possiblePlacements().size(),
                            piecePossiblePlacements.stream().filter(p -> p.piece().equals(p2)).findFirst().orElseThrow().possiblePlacements().size()
                    );
                    if (compare != 0) {
                        return compare;
                    }
                    return Integer.compare(p1.id(), p2.id());
                }).orElseThrow();
    }

    public Optional<Placement> nextPossible(Piece piece) {
        Set<Placement> pieceTriedPlacement = pieceTriedPlacements.stream().filter(p -> p.piece().equals(piece))
                .findFirst().map(PieceTriedPlacement::triedPlacements).orElse(Set.of());
        return piecePossiblePlacements.stream()
                .filter(p -> p.piece().equals(piece)).findFirst().orElseThrow()
                .possiblePlacements()
                .stream()
                .filter(p -> !pieceTriedPlacement.contains(p))
                .findFirst();
    }

    public boolean isSolvable() {
        return !isNotSolvable();
    }

    public boolean isNotSolvable() {
        return piecePossiblePlacements.stream().anyMatch(p -> p.possiblePlacements().isEmpty())
                || positionPossiblePlacements.stream().anyMatch(p -> p.possiblePlacements().isEmpty());
    }

    public PuzzleState clearTried(Piece piece) {
        return new PuzzleState(
                this.piecePossiblePlacements,
                this.positionPossiblePlacements,
                this.pieceTriedPlacements().stream().map(pieceTriedPlacement ->
                        {
                            if (pieceTriedPlacement.piece() == piece) {
                                return new PieceTriedPlacement(pieceTriedPlacement.piece(),
                                        Set.of());
                            } else {
                                return pieceTriedPlacement;
                            }
                        }
                ).toList()
        );
    }

    public PuzzleState tryPlacement(Placement placement, Set<Placement> intersect) {
        List<PiecePossiblePlacement> piecePossiblePlacements1 = this.piecePossiblePlacements.stream()
                .filter(piecePossiblePlacement -> piecePossiblePlacement.piece() != placement.piece())
                .map(piecePossiblePlacement ->
                        piecePossiblePlacement.excludeIntersect(intersect)
                ).toList();
        List<PositionPossiblePlacement> positionPossiblePlacements1 = this.positionPossiblePlacements.stream()
                .filter(positionPossiblePlacement -> !positionPossiblePlacement.position().equals(placement.position()))
                .map(positionPossiblePlacement ->
                        positionPossiblePlacement.excludeIntersect(placement, intersect)
                ).toList();
        var onePiecePositions = positionPossiblePlacements1.stream().filter(positionPossiblePlacement ->
                positionPossiblePlacement.possiblePlacements().stream().map(Placement::piece).distinct().count() == 1
        ).toList();
        if (!onePiecePositions.isEmpty()) {
            var pieces = onePiecePositions.stream().flatMap(o -> o.possiblePlacements().stream().map(Placement::piece)).collect(Collectors.toSet());
            positionPossiblePlacements1 = positionPossiblePlacements1.stream().map(positionPossiblePlacement ->
                    positionPossiblePlacement.excludeOnePiecePositions(onePiecePositions, pieces)
            ).toList();
            piecePossiblePlacements1 = piecePossiblePlacements1.stream().map(piecePossiblePlacement ->
                    piecePossiblePlacement.excludeOnePiecePositions(onePiecePositions, pieces)
            ).toList();
        }
        return new PuzzleState(
                piecePossiblePlacements1,
                positionPossiblePlacements1,
                this.pieceTriedPlacements.stream().map(pieceTriedPlacement ->
                        {
                            Set<Placement> triedPlacements = new HashSet<>(pieceTriedPlacement.triedPlacements());
                            if (pieceTriedPlacement.piece() == placement.piece()) {
                                triedPlacements.add(placement);
                            }
                            return new PieceTriedPlacement(pieceTriedPlacement.piece(),
                                    triedPlacements);
                        }
                ).toList()
        );
    }
}
