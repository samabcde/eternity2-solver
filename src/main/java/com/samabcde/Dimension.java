package com.samabcde;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Dimension(int width, int height) {
    public Dimension {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimension width and height must be positive");
        }
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Dimension width and height less than 2 is not supported for Eternity 2");
        }
    }

    public Stream<Position> allPositions() {
        PositionFactory positionFactory = new PositionFactory(this);
        return IntStream.range(0, width)
                .mapToObj(w -> IntStream.range(0, height).mapToObj(h -> positionFactory.createPosition(w, h)))
                .flatMap(x -> x);
    }

    public Stream<Position> cornerPositions() {
        return allPositions().filter(p->p.positionType().isCorner);
    }

    public Stream<Position> sidePositions() {
        return allPositions().filter(p->p.positionType().isSide);
    }

    public Stream<Position> interiorPositions() {
        return allPositions().filter(p->p.positionType().isInterior);
    }
}
