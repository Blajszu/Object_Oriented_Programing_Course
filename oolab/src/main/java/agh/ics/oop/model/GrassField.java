package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Grass> grassOnMap = new HashMap<>();
    private final Map<Vector2d, Animal> animalsOnMap = new HashMap<>();

    private final MapVisualizer visualizer;

    public GrassField(int numberOfGrassPieces) {

        visualizer = new MapVisualizer(this);
        Random random = new Random();

        //ONLY FOR TESTS
        random.setSeed(123456789);

        while(grassOnMap.size() < numberOfGrassPieces) {
            int x = random.nextInt((int) sqrt(numberOfGrassPieces * 10));
            int y = random.nextInt((int) sqrt(numberOfGrassPieces * 10));

            Vector2d position = new Vector2d(x, y);

            if(!grassOnMap.containsKey(position)) {
                grassOnMap.put(new Vector2d(x, y), new Grass(position));
            }
        }
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position)) {
            animalsOnMap.put(position, animal);
            return true;
        }
        return false;
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

        Animal animal = animalsOnMap.get(position);
        if(animal == null)
            return grassOnMap.get(position);

        return animal;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalsOnMap.containsKey(position);
    }

    @Override
    public String toString() {
        Vector2d leftDown = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d rightUp = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Vector2d position : animalsOnMap.keySet()) {
            leftDown = leftDown.lowerLeft(position);
            rightUp = rightUp.upperRight(position);
        }

        for (Vector2d position : grassOnMap.keySet()) {
            leftDown = leftDown.lowerLeft(position);
            rightUp = rightUp.upperRight(position);
        }

        return visualizer.draw(leftDown, rightUp);
    }
}
