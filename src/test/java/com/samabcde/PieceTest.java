package com.samabcde;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    public void toHumanReadableTest() {
        Piece piece = new Piece(0, new Color('a'), new Color('b'), new Color('c'), new Color('d'));
        assertEquals("""
                
                ╲a╱
                d╳b
                ╱c╲
                
                """, "\n"+piece.toHumanReadable()+"\n");
    }
}