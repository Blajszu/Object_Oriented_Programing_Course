package agh.ics.oop.model

import java.util.*

class BouncyMap(private val width: Int, private val height: Int) : WorldMap {

    private val animalsMap: MutableMap<Vector2d, Animal> = mutableMapOf()

    override fun place(animal: Animal) {

        val position = animal.getPosition()
        if (animalsMap.containsValue(animal)) {
            return
        }

        if (isOccupied(position)) {
            val newPosition = randomFreePosition(getCurrentBounds().upperRight)

            if (newPosition != null) {
                animal.setPosition(newPosition)
                animalsMap[newPosition] = animal
            } else {
                val randomAnimal = animalsMap.values.random()
                val randomPosition = randomAnimal.getPosition()

                animalsMap.remove(randomPosition)

                animal.setPosition(randomPosition)
                animalsMap[randomPosition] = animal
            }
        } else {
            animalsMap[position] = animal
        }
    }


    override fun move(animal: Animal, direction: MoveDirection) {
        val currentPosition = animal.getPosition()

        val newPosition = when (direction) {
            MoveDirection.FORWARD -> currentPosition + animal.getCurrentOrientation().toUnitVector()
            MoveDirection.BACKWARD -> currentPosition - animal.getCurrentOrientation().toUnitVector()
            MoveDirection.LEFT -> currentPosition
            MoveDirection.RIGHT -> currentPosition
        }

        if (currentPosition != newPosition && canMoveTo(newPosition)) {
            animal.move(direction, this)
            animalsMap[newPosition] = animal
            animalsMap.remove(currentPosition)
        } else {
            animal.move(direction, this)
        }
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
                && animalsMap.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Optional<Animal> {
        return Optional.ofNullable(animalsMap[position])
    }

    override fun getCurrentBounds(): Boundary {
        return Boundary(Vector2d(0, 0), Vector2d(width - 1, height - 1))
    }

    override fun getId(): UUID {
        return UUID.randomUUID()
    }

    override fun getOrderedAnimals(): Collection<Animal> {
        return animalsMap.values.toList()
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
    }
}
