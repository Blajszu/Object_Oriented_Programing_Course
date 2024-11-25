package agh.ics.oop.model;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

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

            //then
            assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
            assertEquals(animal2, map.objectAt(new Vector2d(1, 1)));
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
        WorldElement result = map.objectAt(new Vector2d(2, 2));

        //then
        assertEquals(animal1, result);
    }

    @Test
    void objectAtWhenPositionIsFree() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        WorldElement result = map.objectAt(new Vector2d(4, 4));

        //then
        assertNull(result);
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
        assertEquals(new Vector2d(3, 2), animal1.getPosition());
        assertEquals(animal1, map.objectAt(new Vector2d(3, 2)));
        assertNull(map.objectAt(new Vector2d(2, 2)));
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