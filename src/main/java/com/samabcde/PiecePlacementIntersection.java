package com.samabcde;

import java.util.List;

public record PiecePlacementIntersection(Piece piece, List<PlacementIntersection> placementIntersections) {
}
