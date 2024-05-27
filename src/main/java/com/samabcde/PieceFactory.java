package com.samabcde;

public class PieceFactory {
    private int idCounter = 0;

    public Piece createPiece(String value) {
        if (value.length() != 4) {
            throw new IllegalArgumentException("invalid value: [%s]".formatted(value));
        }
        char[] values = value.toCharArray();
        return new Piece(getId(), new Color(values[0]), new Color(values[1]), new Color(values[2]), new Color(values[3]));
    }

    private int getId() {
        int id = idCounter;
        idCounter++;
        return id;
    }
}
