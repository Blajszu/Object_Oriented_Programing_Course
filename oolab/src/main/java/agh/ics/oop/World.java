package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {

        try {
            WorldMap wm = new GrassField(10);
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            Simulation simulation = new Simulation(positions, directions, wm);
            simulation.run();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace(System.err);
        }
    }
}