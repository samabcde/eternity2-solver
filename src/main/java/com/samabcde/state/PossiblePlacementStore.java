package com.samabcde.state;

import com.samabcde.Piece;
import com.samabcde.Position;

import java.util.Map;

public class PossiblePlacementStore {
    // index by piece id
    private PiecePossiblePlacement[] c;
    // index by position id
    private PositionPossiblePlacement[] d;
}
