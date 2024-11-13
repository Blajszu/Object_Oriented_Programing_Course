package agh.ics.oop.model;

public class Animal {
    private static final Vector2d LEFT_DOWN_MAP_CORNER = new Vector2d(0,0);
    private static final Vector2d RIGHT_UP_MAP_CORNER = new Vector2d(4,4);
    
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
