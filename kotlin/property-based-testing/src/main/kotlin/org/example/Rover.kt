package org.example

import org.example.Command.*
import org.example.Direction.*

data class Rover(
    private val facing: Direction = North,
    private val at: Position = at(0, 0),
    private val scanner: Scanner = MalfunctioningScanner,
    private val ignoringCommands: Boolean = false,
) {

    fun receiveCommands(vararg commands: Command): Rover =
        commands.fold(this) { acc, command -> acc.executeCommand(command) }

    private fun executeCommand(command: Command): Rover = viaCircuitBreaker {
        when (command) {
            Forwards -> copy(at =this.at + facing.vector)
            Backwards -> copy(at = this.at - facing.vector)
            Right -> copy(facing = facing.clockwise())
            Left -> copy(facing = facing.counterClockwise())
        }
    }

    private fun viaCircuitBreaker(commandBlock: () -> Rover): Rover {
        if (ignoringCommands) return this
        val newRover = commandBlock()
        return if (scanner.scan(newRover.at) == null) newRover
        else copy(ignoringCommands = true)
    }
}

enum class Command {
    Forwards,
    Backwards,
    Right,
    Left,
}