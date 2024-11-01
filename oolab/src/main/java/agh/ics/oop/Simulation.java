package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moveDirections) {
        moves = moveDirections;
        animals = new ArrayList<>();

        for(Vector2d position : animalPositions ) {
            animals.add(new Animal(position));
        }
    }

    public void run() {
        int numberOfAnimals = animals.size();
        int numberOfMoves = moves.size();

        for(int i = 0; i < numberOfMoves; i++) {
            Animal currentAnimal = animals.get(i % numberOfAnimals);

            currentAnimal.move(moves.get(i));
            System.out.printf("Zwierze %d : %s%n", i % numberOfAnimals, currentAnimal);
        }
    }
}
