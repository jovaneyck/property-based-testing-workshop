package org.example

import kotlin.random.Random

interface Scanner {
    fun scan(position: Position): Obstacle?
}

data class RandomScanner(private val random: Random = Random) : Scanner {
    override fun scan(position: Position): Obstacle? =
        random.nextInt(1, 20).let {
            when (it) {
                1 -> Obstacle.Crater
                2 -> Obstacle.Martian
                else -> null
            }
        }
}

object MalfunctioningScanner : Scanner {
    override fun scan(position: Position) = null
}

enum class Obstacle {
    Crater,
    Martian,
}