package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {
    @Test
    void atCreation_facesNorth() {
        final Rover rover = new Rover();

        assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    void atCreation_locationAtOrigin() {
        final Rover rover = new Rover();

        final Location origin = new Location(0, 0);

        assertThat(rover.getLocation()).isEqualTo(origin);
    }
}