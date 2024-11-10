package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
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

    List<Vector2d> getAnimalsPositionsAfterSimulation() {
        List<Vector2d> positions = new ArrayList<>();
        for(Animal animal : animals) {
            positions.add(animal.getCurrentPosition());
        }

        return positions;
    }

    List<MapDirection> getAnimalOrientationsAfterSimulation() {
        List<MapDirection> orientations = new ArrayList<>();
        for(Animal animal : animals) {
            orientations.add(animal.getCurrentOrientation());
        }

        return orientations;
    }

    public void run() {
        int numberOfAnimals = animals.size();
        int numberOfMoves = moves.size();

        if(numberOfAnimals == 0)
            return;

        for(int i = 0; i < numberOfMoves; i++) {
            Animal currentAnimal = animals.get(i % numberOfAnimals);

            currentAnimal.move(moves.get(i));
            System.out.printf("Zwierze %d : %s%n", i % numberOfAnimals, currentAnimal);
        }
    }
}
