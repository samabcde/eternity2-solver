package com.samabcde;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Eternity2SolverTest {

    @Nested
    public class _2x2 {

        @Test
        public void solve() {
            PieceFactory pieceFactory = new PieceFactory();
            Eternity2Solver solver = new Eternity2Solver(new Grid(new Dimension(2, 2)),
                    List.of(pieceFactory.createPiece("aabc"), pieceFactory.createPiece("baad"), pieceFactory.createPiece("edaa"), pieceFactory.createPiece("acea")),
                    List.of(new Placement(pieceFactory.createPiece("aabc"), new PositionFactory(new Dimension(2, 2)).createPosition("1,0"), Rotation._0)));
            Solution solution = solver.solve();
            assertEquals("""
                     a  a
                    a cc a
                     e  b
                    a dd a
                     a  a
                    """, solution.toHumanReadable());
        }
    }
}
