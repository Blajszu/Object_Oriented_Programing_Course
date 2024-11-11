package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {

    private final List<T> objectsOnMap;
    private final List<MoveDirection> moves;
    private final WorldMap<T, P> worldMap;

    public Simulation(List<T> objects, List<MoveDirection> moveDirections, WorldMap<T, P> map) {

        moves = moveDirections;
        worldMap = map;
        objectsOnMap = new ArrayList<>();

        objects.forEach(object -> {
            if(map.place(object))
                objectsOnMap.add(object);
        });
    }

    public void run() {
        int numberOfObjects = objectsOnMap.size();
        int numberOfMoves = moves.size();

        if(numberOfObjects == 0)
            return;

        for(int i = 0; i < numberOfMoves; i++) {
            T currentObject = objectsOnMap.get(i % numberOfObjects);

            worldMap.move(currentObject, moves.get(i));
            System.out.print(worldMap);
        }
    }
}
