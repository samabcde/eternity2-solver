package com.samabcde;

import java.util.List;

public record PlacementIntersection(
        Placement placement,
        List<Placement> intersections
) {

}
