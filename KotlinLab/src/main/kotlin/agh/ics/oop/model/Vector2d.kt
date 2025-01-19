package agh.ics.oop.model

import kotlin.math.max
import kotlin.math.min

data class Vector2d(val x: Int, val y: Int) {

    override fun toString() = "($x, $y)"

    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)

    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)

    operator fun compareTo(other: Vector2d) = when {
        this.follows(other) && this.precedes(other) -> 0
        this.follows(other) -> 1
        else -> -1
    }

    fun precedes(other: Vector2d) = x <= other.x && y <= other.y

    fun follows(other: Vector2d) = x >= other.x && y >= other.y

    fun upperRight(other: Vector2d) = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d) = Vector2d(min(x, other.x), min(y, other.y))
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.EAST -> Vector2d(1, 0)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
    }
}