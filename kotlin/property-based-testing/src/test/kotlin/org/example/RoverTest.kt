package org.example

import org.assertj.core.api.Assertions.assertThat
import org.example.Command.*
import org.example.Direction.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

class RoverTest {
    @ParameterizedTest
    @MethodSource("forwardsCriteria")
    fun `A Rover can Move forwards`(startingPosition: Position, facing: Direction, expectedPosition: Position) {
        val initialRover = Rover(facing = facing, at = startingPosition)
        val actual = initialRover.receiveCommands(Forwards)
        val expectedRover = Rover(facing = facing, at = expectedPosition)
        assertThat(actual).isEqualTo(expectedRover)
    }

    @ParameterizedTest
    @MethodSource("backwardsCriteria")
    fun `A Rover can Move backwards`(startingPosition: Position, facing: Direction, expectedPosition: Position) {
        val initialRover = Rover(facing = facing, at = startingPosition)
        val actual = initialRover.receiveCommands(Backwards)
        val expectedRover = Rover(facing = facing, at = expectedPosition)
        assertThat(actual).isEqualTo(expectedRover)
    }

    @Test
    fun `A Rover can turn right and left`() {
        val rover = Rover(facing = North)
        rover.receiveCommands(Right)
            .also { assertThat(it).isEqualTo(Rover(East)) }
            .receiveCommands(Right)
            .also { assertThat(it).isEqualTo(Rover(South)) }
            .receiveCommands(Right)
            .also { assertThat(it).isEqualTo(Rover(West)) }
            .receiveCommands(Right)
            .also { assertThat(it).isEqualTo(Rover(North)) }
            .receiveCommands(Left)
            .also { assertThat(it).isEqualTo(Rover(West)) }
            .receiveCommands(Left)
            .also { assertThat(it).isEqualTo(Rover(South)) }
            .receiveCommands(Left)
            .also { assertThat(it).isEqualTo(Rover(East)) }
            .receiveCommands(Left)
            .also { assertThat(it).isEqualTo(Rover(North)) }
    }

    @Test
    fun `A Rover stops executing when it encountered an obstacle`() {
        val scanner = RandomScanner(Random(5))
        val initialRover = Rover(scanner = scanner) //Will scan something on the 5th move

        val actual = initialRover.receiveCommands(Forwards, Forwards, Forwards, Forwards, Forwards, Forwards, Backwards)

        assertThat(actual).isEqualTo(Rover(scanner = scanner, at = at(0, 4), ignoringCommands = true))
    }

    companion object {
        @JvmStatic
        fun forwardsCriteria() = listOf(
            arguments(at(10, 10), North, at(10, 11)),
            arguments(at(10, 10), East, at(11, 10)),
            arguments(at(10, 10), South, at(10, 9)),
            arguments(at(10, 10), West, at(9, 10)),
        )

        @JvmStatic
        fun backwardsCriteria() = listOf(
            arguments(at(10, 10), North, at(10, 9)),
            arguments(at(10, 10), East, at(9, 10)),
            arguments(at(10, 10), South, at(10, 11)),
            arguments(at(10, 10), West, at(11, 10)),
        )
    }
}