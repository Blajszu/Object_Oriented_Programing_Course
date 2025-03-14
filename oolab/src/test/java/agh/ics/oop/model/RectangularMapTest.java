package agh.ics.oop.model;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    // TESTY METODY PLACE

    @Test
    void placeAnimalOnFreePosition() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(1, 1));

        //when
        try {
            map.place(animal1);
            map.place(animal2);

            Optional<WorldElement> element1 = map.objectAt(new Vector2d(2, 2));
            Optional<WorldElement> element2 = map.objectAt(new Vector2d(1, 1));

            //then

            if(element1.isPresent() && element2.isPresent()) {
                assertEquals(animal1, element1.get());
                assertEquals(animal2, element2.get());
            }
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void placeAnimalOnOccupiedPositionThrowsException() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the first animal: " + e.getMessage());
        }

        //when & then
        Animal animal2 = new Animal(new Vector2d(2, 2));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
    }

    @Test
    void placeAnimalOutsideMapBoundariesThrowsException() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(5, 5));

        //when & then
        assertThrows(IncorrectPositionException.class, () -> map.place(animal));
    }

    // POZOSTAŁE TESTY BEZ ZMIAN

    @Test
    void canMoveToFreePositionWithinBoundaries() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        boolean result = map.canMoveTo(new Vector2d(3, 3));

        //then
        assertTrue(result);
    }

    @Test
    void canMoveToOccupiedPosition() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        boolean result = map.canMoveTo(new Vector2d(2, 2));

        //then
        assertFalse(result);
    }

    @Test
    void canMoveToPositionOutsideBoundaries() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        boolean result = map.canMoveTo(new Vector2d(-1, 0));

        //then
        assertFalse(result);
    }

    @Test
    void isOccupiedWhenPositionIsOccupied() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        boolean result = map.isOccupied(new Vector2d(2, 2));

        //then
        assertTrue(result);
    }

    @Test
    void isOccupiedWhenPositionIsFree() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        boolean result = map.isOccupied(new Vector2d(0, 0));

        //then
        assertFalse(result);
    }

    @Test
    void objectAtWhenPositionIsOccupied() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        Optional<WorldElement> result = map.objectAt(new Vector2d(2, 2));

        //then
        result.ifPresent(worldElement -> assertEquals(animal1, worldElement));
    }

    @Test
    void objectAtWhenPositionIsFree() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Optional<WorldElement> result = map.objectAt(new Vector2d(4, 4));

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void animalMovementCorrect() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        map.move(animal1, MoveDirection.RIGHT);
        map.move(animal1, MoveDirection.FORWARD);

        //then
        Optional<WorldElement> element = map.objectAt(new Vector2d(3, 2));

        assertEquals(new Vector2d(3, 2), animal1.getPosition());
        element.ifPresent(worldElement -> assertEquals(animal1, worldElement));
        assertTrue(map.objectAt(new Vector2d(2, 2)).isEmpty());
    }

    @Test
    void animalOutOfBoundsMove() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal1);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal1, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 4), animal1.getPosition());
    }

    @Test
    void moveToOldOccupiedPosition() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 1));

        try {
            map.place(animal1);
            map.place(animal2);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing animals: " + e.getMessage());
        }

        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(2, 2), animal2.getPosition());
    }
    
    @Test
    void testGetOrderedAnimals() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        Animal animal3 = new Animal(new Vector2d(3, 3));
    
        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing animals: " + e.getMessage());
        }
    
        //when
        List<Animal> orderedAnimals = (List<Animal>) map.getOrderedAnimals();
    
        //then
        List<Animal> expectedOrder = List.of(animal2, animal1, animal3);
        assertEquals(expectedOrder, orderedAnimals);
    }

    @Test
    void mapToString() {
        //given
        WorldMap map = new RectangularMap(5, 5);

        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(1, 1));

        //when
        try {
            map.place(animal1);
            map.place(animal2);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing animals: " + e.getMessage());
        }

        //then
        String expectedMap = " y\\x  0 1 2 3 4\r\n  5: -----------\r\n  4: | | | | | |\r\n  3: | | | | | |\r\n  2: | | |^| | |\r\n  1: | |^| | | |\r\n  0: | | | | | |\r\n -1: -----------\r\n";

        assertEquals(expectedMap, map.toString());
    }
}