package agh.ics.oop.model

class Animal(
    private var currentPosition: Vector2d = Vector2d(2, 2),
    private var currentOrientation: MapDirection = MapDirection.NORTH
) {

    fun getCurrentOrientation(): MapDirection = currentOrientation

    fun getPosition(): Vector2d = currentPosition

    fun setPosition(newPosition: Vector2d) {
        this.currentPosition = newPosition
    }

    override fun toString(): String = when (currentOrientation) {
        MapDirection.NORTH -> "^"
        MapDirection.SOUTH -> "v"
        MapDirection.EAST -> ">"
        MapDirection.WEST -> "<"
    }

    fun isAt(position: Vector2d): Boolean = currentPosition == position

    fun move(direction: MoveDirection?, validator: MoveValidator) {
        if (direction == null) return

        val newPosition = when (direction) {
            MoveDirection.LEFT -> {
                currentOrientation = currentOrientation.previous()
                currentPosition
            }
            MoveDirection.RIGHT -> {
                currentOrientation = currentOrientation.next()
                currentPosition
            }
            MoveDirection.FORWARD -> currentPosition + currentOrientation.toUnitVector() // Użycie rozszerzenia
            MoveDirection.BACKWARD -> currentPosition - currentOrientation.toUnitVector() // Użycie rozszerzenia
        }

        if (validator.canMoveTo(newPosition)) {
            currentPosition = newPosition
        }
    }
}
