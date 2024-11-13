package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animalsAtPositions = new HashMap<>();

    private final Vector2d leftDownMapCorner = new Vector2d(0,0);
    private final Vector2d rightUpMapCorner;

    private final MapVisualizer visualizer;

    public RectangularMap(int height, int width) {

        rightUpMapCorner = new Vector2d(width - 1, height - 1);
        visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getCurrentPosition();

        if (canMoveTo(position)) {
            animalsAtPositions.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal == null || !animalsAtPositions.containsValue(animal))
            return;

        Vector2d oldPosition = animal.getCurrentPosition();
        animal.move(direction, this);

        if(!oldPosition.equals(animal.getCurrentPosition())) {
            animalsAtPositions.remove(oldPosition);
            animalsAtPositions.put(animal.getCurrentPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalsAtPositions.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animalsAtPositions.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(leftDownMapCorner) &&
                position.precedes(rightUpMapCorner) &&
                !isOccupied(position));
    }

    @Override
    public String toString() {
        return visualizer.draw(leftDownMapCorner, rightUpMapCorner);
    }
}