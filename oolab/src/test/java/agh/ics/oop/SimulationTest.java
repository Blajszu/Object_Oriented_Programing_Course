package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
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
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 2));
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        // Then
        assertEquals(2, animalPositions.size());
        for (Vector2d pos : animalPositions) {
            assertTrue(pos.follows(new Vector2d(0, 0)));
            assertTrue(pos.precedes(new Vector2d(4, 4)));
        }
    }

    @Test
    public void testSimulationRunWithSingleAnimal() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        );
        List<Vector2d> positions = List.of(new Vector2d(0, 0));
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();
        Vector2d position = animalPositions.getFirst();
        Vector2d expectedPosition = new Vector2d(0, 1);

        // Then
        assertEquals(1, animalPositions.size());
        assertEquals(expectedPosition, position);
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
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        // Then
        assertEquals(2, animalPositions.size());
        assertEquals(new Vector2d(4, 4), animalPositions.get(0));
        assertEquals(new Vector2d(0, 0), animalPositions.get(1));
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
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        // Then
        assertEquals(3, animalPositions.size());
        assertEquals(new Vector2d(1, 2), animalPositions.get(0));
        assertEquals(new Vector2d(4, 2), animalPositions.get(1));
        assertEquals(new Vector2d(3, 4), animalPositions.get(2));
    }

    @Test
    public void testSimulationWithNoDirections() {
        // Given
        List<MoveDirection> directions = List.of();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        // Then
        assertEquals(1, animalPositions.size());
        assertEquals(new Vector2d(2, 2), animalPositions.getFirst());
    }

    @Test
    public void testSimulationWithNoAnimals() {
        // Given
        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        );
        List<Vector2d> positions = List.of();
        Simulation simulation = new Simulation(positions, directions);

        // When
        simulation.run();
        List<Vector2d> animalPositions = simulation.getAnimalsPositionsAfterSimulation();

        // Then
        assertEquals(0, animalPositions.size());
    }
}
