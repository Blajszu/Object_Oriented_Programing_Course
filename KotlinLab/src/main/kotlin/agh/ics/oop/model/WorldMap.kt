package agh.ics.oop.model

import java.util.*

interface WorldMap : MoveValidator {

    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Optional<Animal>

    fun getCurrentBounds(): Boundary

    fun getId(): UUID

    fun getOrderedAnimals(): Collection<Animal>
}
