package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.List;

public class World {
    public static void main(String[] args) {

        try {
            GrassField gf = new GrassField(10);
            RectangularMap rm = new RectangularMap(10, 10);

            gf.addObserver(new ConsoleMapDisplay());
            rm.addObserver(new ConsoleMapDisplay());

            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            Simulation simulation1 = new Simulation(positions, directions, gf);
            Simulation simulation2 = new Simulation(positions, directions, rm);

            SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));
            engine.runSync();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace(System.err);
        }
    }
}