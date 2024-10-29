package agh.ics.oop.model;

public class Animal {
    private static final Vector2d LEFT_DOWN_MAP_CORNER = new Vector2d(0,0);
    private static final Vector2d RIGHT_UP_MAP_CORNER = new Vector2d(4,4);
    
    private MapDirection currentOrientation;
    private Vector2d currentPositionOnMap;

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        currentPositionOnMap = position;
        currentOrientation = MapDirection.NORTH;
    }

    public MapDirection getCurrentOrientation() {
        return currentOrientation;
    }

    public Vector2d getCurrentPositionOnMap() {
        return currentPositionOnMap;
    }

    @Override
    public String toString() {
        return "Pozycja na mapie: %s, Orientacja: %s".formatted(currentPositionOnMap.toString(), currentOrientation.toString());
    }

    public boolean isAt(Vector2d position) {
        return currentPositionOnMap.equals(position);
    }

    public void move(MoveDirection direction) {
        if(direction == null)
            return;

        Vector2d newPosition = currentPositionOnMap;

        switch (direction) {
            case MoveDirection.LEFT:
                currentOrientation = currentOrientation.previous();
                break;
            case MoveDirection.RIGHT:
                currentOrientation = currentOrientation.next();
                break;
            case MoveDirection.FORWARD:
                newPosition = currentPositionOnMap.add(currentOrientation.toUnitVector());
                break;
            case MoveDirection.BACKWARD:
                newPosition = currentPositionOnMap.subtract(currentOrientation.toUnitVector());
                break;
        }

        if(
            (newPosition.getX() >= LEFT_DOWN_MAP_CORNER.getX() && newPosition.getY() >= LEFT_DOWN_MAP_CORNER.getY()) &&
            (newPosition.getX() <= RIGHT_UP_MAP_CORNER.getX() && newPosition.getY() <= RIGHT_UP_MAP_CORNER.getY())
        ) currentPositionOnMap = newPosition;
    }
}
