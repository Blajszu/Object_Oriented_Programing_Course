package agh.ics.oop.model

import kotlin.random.Random

fun WorldMap.randomPosition(): Vector2d {
    val bounds = this.getCurrentBounds()

    val randomX = Random.nextInt(bounds.lowerLeft.x, bounds.upperRight.x + 1)
    val randomY = Random.nextInt(bounds.lowerLeft.y, bounds.upperRight.y + 1)

    return Vector2d(randomX, randomY)
}

fun WorldMap.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val possiblePositions = generateSequence {
        Vector2d(Random.nextInt(0, mapSize.x), Random.nextInt(0, mapSize.y))
    }.takeWhile { position ->
        !this.isOccupied(position)
    }.toList()

    return possiblePositions.randomOrNull()
}