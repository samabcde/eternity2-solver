package com.samabcde;

import java.util.List;

public class SolverApplication {
    public static void main(String[] args) {
        Eternity2Solver solver = new Eternity2Solver(new Grid(Eternity2Setup.dimension), Eternity2Setup.pieces, List.of());
        Solution solution = solver.solve();

    }
}
