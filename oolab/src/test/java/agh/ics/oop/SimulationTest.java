package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {

    @Test
    public void testSimulationRunWithValidDirections() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();

        // Then
        assertEquals(2, animalPositions.size());
        assertEquals(2, animalOrientations.size());

        for (Vector2d pos : animalPositions) {
            assertTrue(pos.follows(new Vector2d(0, 0)));
            assertTrue(pos.precedes(new Vector2d(4, 4)));
        }

        assertEquals(MapDirection.EAST, animalOrientations.get(0));
        assertEquals(MapDirection.EAST, animalOrientations.get(1));
    }

    @Test
    public void testSimulationRunWithSingleAnimal() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        );
        List<Vector2d> positions = List.of(new Vector2d(0, 0));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();
        Vector2d position = animalPositions.getFirst();

        // Then
        assertEquals(1, animalPositions.size());
        assertEquals(new Vector2d(0, 1), position);
        assertEquals(MapDirection.EAST, animalOrientations.getFirst());
    }

    @Test
    public void testBoundaryConditions() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );
        List<Vector2d> positions = List.of(new Vector2d(4, 4), new Vector2d(0, 0));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();

        // Then
        assertEquals(2, animalPositions.size());
        assertEquals(new Vector2d(4, 4), animalPositions.get(0));
        assertEquals(new Vector2d(0, 0), animalPositions.get(1));
        assertEquals(MapDirection.EAST, animalOrientations.get(0));
        assertEquals(MapDirection.SOUTH, animalOrientations.get(1));
    }

    @Test
    public void testMultipleAnimalsSimulation() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.FORWARD
        );
        List<Vector2d> positions = List.of(
                new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(3, 3)
        );
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();

        // Then
        assertEquals(3, animalPositions.size());
        assertEquals(new Vector2d(1, 2), animalPositions.get(0));
        assertEquals(new Vector2d(4, 2), animalPositions.get(1));
        assertEquals(new Vector2d(3, 4), animalPositions.get(2));

        assertEquals(MapDirection.NORTH, animalOrientations.get(0));
        assertEquals(MapDirection.EAST, animalOrientations.get(1));
        assertEquals(MapDirection.NORTH, animalOrientations.get(2));
    }

    @Test
    public void testSimulationWithNoDirections() {
        // Given
        List<MoveDirection> directions = List.of();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();

        // Then
        assertEquals(1, animalPositions.size());
        assertEquals(new Vector2d(2, 2), animalPositions.getFirst());
        assertEquals(MapDirection.NORTH, animalOrientations.getFirst());
    }

    @Test
    public void testSimulationWithNoAnimals() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        );
        List<Vector2d> positions = List.of();
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        List<MapDirection> animalOrientations = simulation.getAnimalOrientationsAfterSimulation();

        // Then
        assertEquals(0, animalPositions.size());
        assertEquals(0, animalOrientations.size());
    }

    @Test
    void simulationWithFollowingPositions() {
        //given
        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 1));

        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5, 5));

        //when
        simulation.run();

        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        //then
        assertEquals(new Vector2d(2, 3), animalPositions.getFirst());
        assertEquals(new Vector2d(2,2), animalPositions.get(1));
    }
}
