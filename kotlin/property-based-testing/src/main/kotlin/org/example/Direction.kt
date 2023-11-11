package org.example

sealed class Direction(
    val vector: Position,
    val clockwise: () -> Direction,
    val counterClockwise: () -> Direction,
) {
    data object North : Direction(Position(0, 1), clockwise = { East }, counterClockwise = { West })
    data object East : Direction(Position(1, 0), clockwise = { South }, counterClockwise = { North })
    data object South : Direction(Position(0, -1), clockwise = { West }, counterClockwise = { East })
    data object West : Direction(Position(-1, 0), clockwise = { North }, counterClockwise = { South })
}