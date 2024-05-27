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
        PositionFactory positionFactory = new PositionFactory(this);
        return Stream.of(
                positionFactory.createPosition(0, 0),
                positionFactory.createPosition(width - 1, 0),
                positionFactory.createPosition(0, height - 1),
                positionFactory.createPosition(width - 1, height - 1)
        );
    }

    public Stream<Position> sidePositions() {
        PositionFactory positionFactory = new PositionFactory(this);
        return Stream.concat(
                IntStream.range(0, width)
                        .mapToObj(w -> IntStream.of(0, height - 1).mapToObj(h -> positionFactory.createPosition(w, h)))
                        .flatMap(x -> x),
                IntStream.of(0, width - 1)
                        .mapToObj(w -> IntStream.range(0, height).mapToObj(h -> positionFactory.createPosition(w, h)))
                        .flatMap(x -> x)
        );
    }

    public Stream<Position> interiorPositions() {
        PositionFactory positionFactory = new PositionFactory(this);
        return null;
    }
}
