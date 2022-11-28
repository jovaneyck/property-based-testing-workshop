package org.example;

import org.junit.jupiter.api.Test;

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
}