package com.samabcde;

import com.samabcde.puzzles.Puzzle;

import java.util.List;

public class Eternity2Setup {
    static final char BORDER_COLOR_CODE = 'a';
    public final Puzzle puzzle;
    public final Dimension dimension;
    public final List<Piece> pieces;
    public Eternity2Setup(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.dimension = puzzle.getDimension();
        PieceFactory pieceFactory = new PieceFactory();
        this.pieces = puzzle.getPieces().stream().map(pieceFactory::createPiece).toList();
    }

}
