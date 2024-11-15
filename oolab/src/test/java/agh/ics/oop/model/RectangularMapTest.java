package agh.ics.oop.model;
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
        boolean placed1 = map.place(animal1);
        boolean placed2 = map.place(animal2);

        //then
        assertTrue(placed1);
        assertTrue(placed2);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, map.objectAt(new Vector2d(1, 1)));
    }

    @Test
    void placeAnimalOnOccupiedPosition() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        map.place(animal1);

        //when
        boolean result = map.place(new Animal(new Vector2d(2, 2)));

        //then
        assertFalse(result);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void placeAnimalOutsideMapBoundaries() {
        //given
        RectangularMap map = new RectangularMap(5, 5);

        //when
        boolean result = map.place(new Animal(new Vector2d(5, 5)));

        //then
        assertFalse(result);
        assertNull(map.objectAt(new Vector2d(5, 5)));
    }

    // TESTY METODY CANMOVETO

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
        map.place(animal1);

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

    // TESTY METODY ISOCCUPIED

    @Test
    void isOccupiedWhenPositionIsOccupied() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        map.place(animal1);

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

    // TESTY METODY OBJECTAT

    @Test
    void objectAtWhenPositionIsOccupied() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        map.place(animal1);

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

    // DODATKOWE TESTY RUCHU

    @Test
    void animalMovementCorrect() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        map.place(animal1);

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
        map.place(animal1);

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

        map.place(animal1);
        map.place(animal2);

        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.FORWARD);

        assertEquals(new Vector2d(2,3), animal1.getPosition());
        assertEquals(new Vector2d(2,2), animal2.getPosition());
    }

    // TEST WYŚWIETLANIA MAPY

    @Test
    void mapToString() {
        //given
        WorldMap map = new RectangularMap(5, 5);

        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(1, 1));

        //when
        map.place(animal1);
        map.place(animal2);

        //then
        String expectedMap = " y\\x  0 1 2 3 4\r\n  5: -----------\r\n  4: | | | | | |\r\n  3: | | | | | |\r\n  2: | | |^| | |\r\n  1: | |^| | | |\r\n  0: | | | | | |\r\n -1: -----------\r\n";

        assertEquals(map.toString(), expectedMap);
    }
}