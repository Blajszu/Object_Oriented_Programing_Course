package agh.ics.oop

import agh.ics.oop.model.*

fun main() {
    val bouncyMap = BouncyMap(5, 5)
    val animal = Animal(Vector2d(1, 1))
    bouncyMap.place(animal)

    // Move the animal right
    bouncyMap.move(animal, MoveDirection.RIGHT)
    println(animal.getPosition())
}