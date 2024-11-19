package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animalsOnMap = new HashMap<>();
    protected final MapVisualizer visualizer;

    public AbstractWorldMap() {
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position))
            animalsOnMap.put(position, animal);
        else
            throw new IncorrectPositionException(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal == null || !animalsOnMap.containsValue(animal))
            return;

        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);

        if(!oldPosition.equals(animal.getPosition())) {
            animalsOnMap.remove(oldPosition);
            animalsOnMap.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalsOnMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalsOnMap.containsKey(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animalsOnMap.values());
    }
}
