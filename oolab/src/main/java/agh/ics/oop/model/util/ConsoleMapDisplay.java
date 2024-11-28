package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

public class ConsoleMapDisplay implements MapChangeListener {

    private int eventsCounter = 1;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap);
        System.out.println("Events counter: " + eventsCounter);
        System.out.println("================\n");
        eventsCounter++;
    }
}