package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;

public class World {
    public static void main(String[] args) {

        WorldMap<Animal, Vector2d> wm = new RectangularMap(5, 5);
        List<MoveDirection> directions = OptionsParser.parse(args);

        List<Animal> animals = List.of(new Animal(new Vector2d(2,2)), new Animal(new Vector2d(3,4)));
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, wm);

        simulation.run();

        //Dodatkowa symulacja dla napisów

        List<String> list = List.of("Ala", "ma", "kota", "i", "psa");

        WorldMap<String, Integer> map = new TextMap();
        Simulation<String, Integer> simulation2 = new Simulation<>(list, directions, map);

        simulation2.run();
    }
}