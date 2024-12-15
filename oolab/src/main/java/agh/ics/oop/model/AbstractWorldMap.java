package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animalsOnMap = new HashMap<>();
    private final MapVisualizer visualizer;
    private final List<MapChangeListener> observers = new ArrayList<>();
    private final UUID uuid = UUID.randomUUID();

    public AbstractWorldMap() {
        this.visualizer = new MapVisualizer(this);
    }

    public UUID getId() {
        return uuid;
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    private void mapChangeEvent(String message) {
        for(MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position)) {
            animalsOnMap.put(position, animal);
            mapChangeEvent("Animal placed at " + animal.getPosition());
        }
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
            mapChangeEvent("Animal moved from %s to %s ".formatted(oldPosition, animal.getPosition()));
            return;
        }
        mapChangeEvent("Animal turn to %s ".formatted(direction.toString()));
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

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return visualizer.draw(currentBounds.lowerLeft(), currentBounds.upperRight());
    }
}