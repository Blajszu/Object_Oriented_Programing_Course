package agh.ics.oop.model;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class GrassFieldTest {

    // TESTY METODY PLACE

    @Test
    void placeAnimalOnFreePosition() {
        //given
        GrassField map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(1, 1));

        //when
        try {
            map.place(animal1);
            map.place(animal2);

            Optional<WorldElement> element1 = map.objectAt(new Vector2d(2, 2));
            Optional<WorldElement> element2 = map.objectAt(new Vector2d(1, 1));

            //then
            if(element1.isEmpty() || element2.isEmpty()) fail("Elements should exist on the map");
            else {
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
        GrassField map = new GrassField(10);
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
    void placeAnimalOnGrassPosition() {
        //given
        GrassField map = new GrassField(10);
        Vector2d grassPosition = findGrassPosition(map, 10);
        assertNotNull(grassPosition, "Grass should exist on the map");

        Animal animal = new Animal(grassPosition);

        //when
        try {
            map.place(animal);
            Optional<WorldElement> element = map.objectAt(grassPosition);

            //then
            if(element.isEmpty()) fail("Element should exist on the map");
            else {
                assertFalse(element.get() instanceof Grass, "Grass should be replaced by the animal");
                assertEquals(animal, element.get());
            }
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // TESTY METODY CANMOVETO

    @Test
    void canMoveToFreePosition() {
        //given
        GrassField map = new GrassField(0);

        //when
        boolean result = map.canMoveTo(new Vector2d(2, 2));

        //then
        assertTrue(result);
    }

    @Test
    void canMoveToOccupiedPosition() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        //when
        boolean result = map.canMoveTo(new Vector2d(2, 2));

        //then
        assertFalse(result);
    }

    @Test
    void canMoveToGrassPosition() {
        //given
        GrassField map = new GrassField(1);
        Vector2d grassPosition = new Vector2d(1, 0);

        //when
        boolean result = map.canMoveTo(grassPosition);

        //then
        assertTrue(result);
    }

    // TESTY METODY ISOCCUPIED

    @Test
    void isOccupiedWhenPositionIsOccupiedByAnimal() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        //when
        boolean result = map.isOccupied(new Vector2d(2, 2));

        //then
        assertTrue(result);
    }

    @Test
    void isOccupiedWhenPositionIsOccupiedByGrass() {
        //given
        GrassField map = new GrassField(1);
        Vector2d grassPosition = findGrassPosition(map, 10);

        //when
        boolean result = map.isOccupied(grassPosition);

        //then
        assertTrue(result);
    }

    @Test
    void isOccupiedWhenPositionIsFree() {
        //given
        GrassField map = new GrassField(0);

        //when
        boolean result = map.isOccupied(new Vector2d(2, 2));

        //then
        assertFalse(result);
    }

    // TESTY METODY OBJECTAT

    @Test
    void objectAtWhenPositionIsOccupiedByAnimal() {
        //given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        boolean isOccupied = map.isOccupied(new Vector2d(2, 2));

        //then
        assertTrue(isOccupied, "Position should be occupied by the animal");
    }

    @Test
    void objectAtWhenPositionIsOccupiedByGrass() {
        //given
        GrassField map = new GrassField(1);
        Vector2d grassPosition = findGrassPosition(map, 10);

        //when
        Optional<WorldElement> result = map.objectAt(grassPosition);

        //then
        if(result.isEmpty()) fail("Grass should exist on the map");
            assertInstanceOf(Grass.class, result.get());
    }

    @Test
    void objectAtWhenPositionIsFree() {
        //given
        GrassField map = new GrassField(10);
        Vector2d freePosition = findFreePosition(map, 10);

        //when
        Optional<WorldElement> result = map.objectAt(freePosition);

        //then
        assertTrue(result.isEmpty(), "Position should be free");
    }

    // TESTY METODY MOVE

    @Test
    void moveAnimalToFreePosition() {
        //given
        GrassField map = new GrassField(0);
        Animal animal = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception while placing the animal: " + e.getMessage());
        }

        //when
        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.FORWARD);

        Optional<WorldElement> element1 = map.objectAt(new Vector2d(3, 2));
        Optional<WorldElement> element2 = map.objectAt(new Vector2d(2, 2));

        //then
        assertEquals(new Vector2d(3, 2), animal.getPosition());
        element1.ifPresent(worldElement -> assertEquals(animal, worldElement));
        assertTrue(element2.isEmpty());
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

    // TEST WYŚWIETLANIA MAPY

    @Test
    void mapToString() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(2);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        //when
        String mapString = map.toString();

        //then
        assertNotNull(mapString);
        assertTrue(mapString.contains("^"));
        assertTrue(mapString.contains("*"));
    }

    private Vector2d findGrassPosition(GrassField map, int n) {
        for (int x = 0; x < sqrt(n*10); x++) {
            for (int y = 0; y < sqrt(n*10); y++) {
                Vector2d position = new Vector2d(x, y);
                Optional<WorldElement> element = map.objectAt(position);
                if (element.isPresent() && element.get() instanceof Grass) {
                    return position;
                }
            }
        }
        return null;
    }

    private Vector2d findFreePosition(GrassField map, int n) {
        for (int x = 0; x < sqrt(n*10); x++) {
            for (int y = 0; y < sqrt(n*10); y++) {
                Vector2d position = new Vector2d(x, y);
                Optional<WorldElement> element = map.objectAt(position);
                if (element.isEmpty()) {
                    return position;
                }
            }
        }
        return null;
    }
}

