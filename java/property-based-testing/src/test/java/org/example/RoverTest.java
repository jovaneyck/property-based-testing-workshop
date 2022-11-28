package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Rover.roverBuilder;

class RoverTest {
    @Test
    void atCreation_default_facesNorth() {
        final Rover rover = roverBuilder().build();

        assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    void atCreation_whenStartingDirection_facesDirection() {
        final Direction direction = Direction.WEST;
        final Rover rover = roverBuilder()
                .withStartingDirection(direction)
                .build();

        assertThat(rover.getDirection()).isEqualTo(direction);
    }

    @Test
    void atCreation_default_locationAtOrigin() {
        final Location origin = new Location(0, 0);

        final Rover rover = roverBuilder().build();

        assertThat(rover.getLocation()).isEqualTo(origin);
    }

    @Test
    void atCreation_withStartingLocation_usesLocation() {
        final Location location = new Location(1, 2);

        final Rover rover = roverBuilder()
                .withStartingLocation(location)
                .build();

        assertThat(rover.getLocation()).isEqualTo(location);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void moveForward_doesNotChangeDirection(final Direction direction) {
        final Rover rover = roverBuilder()
                .withStartingDirection(direction)
                .build();

        rover.moveForward();

        assertThat(rover.getDirection()).isEqualTo(direction);
    }

    @Test
    void moveForward_whenFacingNorth_onlyChangesX() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart + 1, yStart);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.NORTH)
                .withStartingLocation(startLocation)
                .build();

        rover.moveForward();

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveForward_whenFacingEast_onlyChangesY() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart, yStart + 1);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.EAST)
                .withStartingLocation(startLocation)
                .build();

        rover.moveForward();

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveForward_whenFacingSouth_onlyChangesX() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart - 1, yStart);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.SOUTH)
                .withStartingLocation(startLocation)
                .build();

        rover.moveForward();

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveForward_whenFacingWest_onlyChangesY() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart, yStart - 1);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.WEST)
                .withStartingLocation(startLocation)
                .build();

        rover.moveForward();

        assertThat(rover.getLocation()).isEqualTo(expected);
    }
}