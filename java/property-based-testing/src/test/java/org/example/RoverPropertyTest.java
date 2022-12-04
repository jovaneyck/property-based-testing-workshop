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

    @Property
    void anyTwoCommand_roverAlwaysChanged(@ForAll Command command1, @ForAll Command command2) {
        final Rover rover = roverBuilder().build();

        rover.processCommand(command1)
                .processCommand(command2);

        //TODO: FIXME
        assertThat(rover).isNotEqualTo(roverBuilder().build());
    }
}
