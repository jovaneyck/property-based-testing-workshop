package org.example

import org.example.Direction.*

fun aDefaultRover(
    facing: Direction = North,
    at: Position = at(0, 0),
    scanner: Scanner = MalfunctioningScanner,
) = Rover(facing = facing, at = at, scanner = scanner)