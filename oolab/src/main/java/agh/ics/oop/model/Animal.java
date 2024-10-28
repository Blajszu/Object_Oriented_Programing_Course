package agh.ics.oop.model;

public class Animal {
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

}
