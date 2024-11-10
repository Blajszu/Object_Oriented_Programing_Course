package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    //Tests for Animal Orientation

    @Test
    public void isInitialOrientationCorrect() {
        //when
        Animal animal = new Animal();

        //then
        assertEquals(MapDirection.NORTH, animal.getCurrentOrientation());
    }

    @Test
    public void rotateLeftOnce() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);

        //then
        assertEquals(MapDirection.WEST, animal.getCurrentOrientation());
    }

    @Test
    public void rotateLeftTwice() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);

        //then
        assertEquals(MapDirection.SOUTH, animal.getCurrentOrientation());
    }

    @Test
    public void rotateLeftThreeTimes() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);

        //then
        assertEquals(MapDirection.EAST, animal.getCurrentOrientation());
    }

    @Test
    public void rotateLeftFourTimes() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);

        //then
        assertEquals(MapDirection.NORTH, animal.getCurrentOrientation());
    }

    @Test
    public void rotateRightOnce() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);

        //then
        assertEquals(MapDirection.EAST, animal.getCurrentOrientation());
    }

    @Test
    public void rotateRightTwice() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        //then
        assertEquals(MapDirection.SOUTH, animal.getCurrentOrientation());
    }

    @Test
    public void rotateRightThreeTimes() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        //then
        assertEquals(MapDirection.WEST, animal.getCurrentOrientation());
    }

    @Test
    public void rotateRightFourTimes() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        //then
        assertEquals(MapDirection.NORTH, animal.getCurrentOrientation());
    }

    //Tests for Animal Position

    @Test
    public void isInitialPositionCorrect() {
        //when
        Animal animal = new Animal();

        //when
        assertEquals(new Vector2d(2, 2), animal.getCurrentPosition());
    }

    @Test
    public void moveForwardNorth() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 3), animal.getCurrentPosition());
    }

    @Test
    public void moveBackwardNorth() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(2, 1), animal.getCurrentPosition());
    }

    @Test
    public void moveForwardEast() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(3, 2), animal.getCurrentPosition());
    }

    @Test
    public void moveBackwardEast() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(1, 2), animal.getCurrentPosition());
    }

    @Test
    public void moveForwardSouth() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 1), animal.getCurrentPosition());
    }

    @Test
    public void moveBackwardSouth() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(2, 3), animal.getCurrentPosition());
    }

    @Test
    public void moveForwardWest() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(1, 2), animal.getCurrentPosition());
    }

    @Test
    public void moveBackwardWest() {
        //given
        Animal animal = new Animal();

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(3, 2), animal.getCurrentPosition());
    }

    //Tests for Map Borders

    @Test
    public void moveBeyondMapLimitsNorth() {
        //given
        Animal animal = new Animal(new Vector2d(2, 4));

        //when
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 4), animal.getCurrentPosition());
    }

    @Test
    public void moveBeyondMapLimitsSouth() {
        //given
        Animal animal = new Animal(new Vector2d(2, 0));

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 0), animal.getCurrentPosition());
    }

    @Test
    public void moveBeyondMapLimitsEast() {
        //given
        Animal animal = new Animal(new Vector2d(4, 2));

        //when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(4, 2), animal.getCurrentPosition());
    }

    @Test
    public void moveBeyondMapLimitsWest() {
        //given
        Animal animal = new Animal(new Vector2d(0, 2));

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(0, 2), animal.getCurrentPosition());
    }
}