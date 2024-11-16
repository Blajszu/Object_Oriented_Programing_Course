package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grassOnMap = new HashMap<>();

    public GrassField(int numberOfGrassPieces) {

        RandomPositionGenerator generator = new RandomPositionGenerator((int) sqrt(numberOfGrassPieces * 10), (int) sqrt(numberOfGrassPieces * 10), numberOfGrassPieces);

        for(Vector2d grassPosition : generator) {
            grassOnMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {

        WorldElement animal = super.objectAt(position);
        if(animal == null)
            return grassOnMap.get(position);

        return animal;
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> result = super.getElements();
        result.addAll(grassOnMap.values());

        return result;
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