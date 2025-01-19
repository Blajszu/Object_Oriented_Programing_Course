package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection currentOrientation;
    private Vector2d currentPosition;

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        currentPosition = position;
        currentOrientation = MapDirection.NORTH;
    }

    public MapDirection getCurrentOrientation() {
        return currentOrientation;
    }

    public Vector2d getPosition() {
        return currentPosition;
    }

    @Override
    public String getResourceName() {
        return "Z %s".formatted(currentPosition.toString());
    }

    @Override
    public String getResourceFileName() {
        return switch (currentOrientation) {
            case NORTH -> "up.png";
            case SOUTH -> "down.png";
            case EAST -> "right.png";
            case WEST -> "left.png";
        };
    }

    @Override
    public String toString() {
        return switch (currentOrientation) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position) {
        return currentPosition.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        if(direction == null)
            return;

        Vector2d newPosition = currentPosition;

        switch (direction) {
            case MoveDirection.LEFT:
                currentOrientation = currentOrientation.previous();
                break;
            case MoveDirection.RIGHT:
                currentOrientation = currentOrientation.next();
                break;
            case MoveDirection.FORWARD:
                newPosition = currentPosition.add(currentOrientation.toUnitVector());
                break;
            case MoveDirection.BACKWARD:
                newPosition = currentPosition.subtract(currentOrientation.toUnitVector());
                break;
        }

        if(validator.canMoveTo(newPosition))
            currentPosition = newPosition;
    }
}
