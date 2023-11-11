package org.example

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import org.assertj.core.api.Assertions.assertThat


class RoverPropertyTest {

    @Property
    fun `anyCommand Rover Always Changed`(@ForAll command: Command) {
        val rover: Rover = aDefaultRover().receiveCommands(command)
        assertThat(rover).isNotEqualTo(aDefaultRover())
    }
}
