package org.example;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Rover.roverBuilder;

public class RoverPropertyTest {

    @Property
    void anyCommand_roverAlwaysChanged(@ForAll Command command) {
        final Rover rover = roverBuilder().build();

        rover.processCommand(command);

        assertThat(rover).isNotEqualTo(roverBuilder().build());
    }
    
}
