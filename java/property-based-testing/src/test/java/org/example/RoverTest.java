package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

        rover.processCommand(Command.MOVE_FORWARD);

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

        rover.processCommand(Command.MOVE_FORWARD);

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

        rover.processCommand(Command.MOVE_FORWARD);

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

        rover.processCommand(Command.MOVE_FORWARD);

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

        rover.processCommand(Command.MOVE_FORWARD);

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void moveBackward_doesNotChangeDirection(final Direction direction) {
        final Rover rover = roverBuilder()
                .withStartingDirection(direction)
                .build();

        rover.processCommand(Command.MOVE_BACKWARD);

        assertThat(rover.getDirection()).isEqualTo(direction);
    }

    @Test
    void moveBackward_whenFacingNorth_onlyChangesX() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart - 1, yStart);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.NORTH)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.MOVE_BACKWARD);

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveBackward_whenFacingEast_onlyChangesY() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart, yStart - 1);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.EAST)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.MOVE_BACKWARD);

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveBackward_whenFacingSouth_onlyChangesX() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart + 1, yStart);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.SOUTH)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.MOVE_BACKWARD);

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @Test
    void moveBackward_whenFacingWest_onlyChangesY() {
        final int xStart = 0;
        final int yStart = 0;

        final Location startLocation = new Location(xStart, yStart);
        final Location expected = new Location(xStart, yStart + 1);

        final Rover rover = roverBuilder()
                .withStartingDirection(Direction.WEST)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.MOVE_BACKWARD);

        assertThat(rover.getLocation()).isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void turnLeft_doesNotChangeLocation(final Direction direction) {
        final Location startLocation = new Location(0, 0);
        final Rover rover = roverBuilder()
                .withStartingDirection(direction)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.TURN_LEFT);

        assertThat(rover.getLocation()).isEqualTo(startLocation);
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, WEST",
            "WEST, SOUTH",
            "SOUTH, EAST",
            "EAST, NORTH"
    })
    void turnLeft_turnsCounterClockwise(final Direction inputDirection, final Direction expectedDirection) {
        final Rover rover = roverBuilder()
                .withStartingDirection(inputDirection)
                .build();

        rover.processCommand(Command.TURN_LEFT);

        assertThat(rover.getDirection()).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void turnRight_doesNotChangeLocation(final Direction direction) {
        final Location startLocation = new Location(0, 0);
        final Rover rover = roverBuilder()
                .withStartingDirection(direction)
                .withStartingLocation(startLocation)
                .build();

        rover.processCommand(Command.TURN_RIGHT);

        assertThat(rover.getLocation()).isEqualTo(startLocation);
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, EAST",
            "WEST, NORTH",
            "SOUTH, WEST",
            "EAST, SOUTH"
    })
    void turnRight_turnsCounterClockwise(final Direction inputDirection, final Direction expectedDirection) {
        final Rover rover = roverBuilder()
                .withStartingDirection(inputDirection)
                .build();

        rover.processCommand(Command.TURN_RIGHT);

        assertThat(rover.getDirection()).isEqualTo(expectedDirection);
    }
}