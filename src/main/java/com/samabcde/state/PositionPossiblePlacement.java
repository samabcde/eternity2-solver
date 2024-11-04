package com.samabcde.state;

import com.samabcde.Placement;
import com.samabcde.Position;

import java.util.List;

/**
 * Possible placement of each position
 */
public record PositionPossiblePlacement(Position position, List<Placement> possiblePlacements) {

}
