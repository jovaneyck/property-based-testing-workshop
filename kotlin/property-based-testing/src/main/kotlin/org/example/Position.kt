package org.example

data class Position(private val x: Int, private val y: Int) {
    operator fun plus(other: Position) = Position(this.x + other.x, this.y + other.y)
    operator fun minus(other: Position) = Position(this.x - other.x, this.y - other.y)
}

fun at(x: Int, y: Int) = Position(x, y)