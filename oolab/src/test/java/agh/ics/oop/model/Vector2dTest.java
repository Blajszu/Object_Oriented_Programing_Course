package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testEqualsMethod() {
        // when
        Vector2d vector1PositiveCoordinates = new Vector2d(1,2);
        Vector2d vector2PositiveCoordinates = new Vector2d(1,2);
        Vector2d vector1NegativeCoordinates = new Vector2d(-3, -7);
        Vector2d vector2NegativeCoordinates = new Vector2d(-3, -7);
        Vector2d vector1ZeroCoordinates = new Vector2d(0, 0);
        Vector2d vector2ZeroCoordinates = new Vector2d(0, 0);

        // then
        assertTrue(vector1PositiveCoordinates.equals(vector2PositiveCoordinates));
        assertTrue(vector1NegativeCoordinates.equals(vector2NegativeCoordinates));
        assertTrue(vector1ZeroCoordinates.equals(vector2ZeroCoordinates));
        assertTrue(vector2PositiveCoordinates.equals(vector2PositiveCoordinates));

        assertFalse(vector1PositiveCoordinates.equals(vector2NegativeCoordinates));
    }

    @Test
    void testToStringMethod() {
        // given
        Vector2d vectorPositiveCoordinates = new Vector2d(1,2);
        Vector2d vectorNegativeCoordinates = new Vector2d(-3, -7);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);

        //when
        String stringPositiveCoordinates = vectorPositiveCoordinates.toString();
        String stringNegativeCoordinates = vectorNegativeCoordinates.toString();
        String stringZeroCoordinates = vectorZeroCoordinates.toString();

        // then
        assertEquals("(1, 2)", stringPositiveCoordinates);
        assertEquals("(-3, -7)", stringNegativeCoordinates);
        assertEquals("(0, 0)", stringZeroCoordinates);
    }

    @Test
    void testPrecedesMethod() {
        // when
        Vector2d vectorPositiveCoordinates = new Vector2d(1,2);
        Vector2d vectorNegativeCoordinates = new Vector2d(-3, -7);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vectorMixedCoordinates = new Vector2d(-3, 6);

        // then
        assertTrue(vectorNegativeCoordinates.precedes(vectorPositiveCoordinates));
        assertTrue(vectorNegativeCoordinates.precedes(vectorZeroCoordinates));
        assertTrue(vectorNegativeCoordinates.precedes(vectorMixedCoordinates));
        assertTrue(vectorPositiveCoordinates.precedes(vectorPositiveCoordinates));

        assertFalse(vectorZeroCoordinates.precedes(vectorNegativeCoordinates));
        assertFalse(vectorZeroCoordinates.precedes(vectorMixedCoordinates));
        assertFalse(vectorPositiveCoordinates.precedes(vectorMixedCoordinates));
        assertFalse(vectorPositiveCoordinates.precedes(vectorNegativeCoordinates));
    }

    @Test
    void testFollowsMethod() {
        // when
        Vector2d vectorPositiveCoordinates = new Vector2d(1,2);
        Vector2d vectorNegativeCoordinates = new Vector2d(-3, -7);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vectorMixedCoordinates = new Vector2d(-3, 6);

        // then
        assertTrue(vectorZeroCoordinates.follows(vectorNegativeCoordinates));
        assertTrue(vectorPositiveCoordinates.follows(vectorNegativeCoordinates));
        assertTrue(vectorPositiveCoordinates.follows(vectorZeroCoordinates));
        assertTrue(vectorNegativeCoordinates.follows(vectorNegativeCoordinates));

        assertFalse(vectorNegativeCoordinates.follows(vectorPositiveCoordinates));
        assertFalse(vectorNegativeCoordinates.follows(vectorZeroCoordinates));
        assertFalse(vectorNegativeCoordinates.follows(vectorMixedCoordinates));
        assertFalse(vectorZeroCoordinates.follows(vectorMixedCoordinates));
        assertFalse(vectorPositiveCoordinates.follows(vectorMixedCoordinates));
    }

    @Test
    void testUpperRightMethod() {
        // given
        Vector2d vector1PositiveCoordinates = new Vector2d(1, 2);
        Vector2d vector2PositiveCoordinates = new Vector2d(3, 1);
        Vector2d vector1NegativeCoordinates = new Vector2d(-4, -3);
        Vector2d vector2NegativeCoordinates = new Vector2d(-2, -5);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vector1TheSameCoordinates = new Vector2d(7, 8);
        Vector2d vector2TheSameCoordinates = new Vector2d(7, 8);
        Vector2d vector1MixedCoordinates = new Vector2d(-1, 4);
        Vector2d vector2MixedCoordinates = new Vector2d(2, -3);

        // when
        Vector2d resultPositiveCoordinates = vector1PositiveCoordinates.upperRight(vector2PositiveCoordinates);
        Vector2d resultNegativeCoordinates = vector1NegativeCoordinates.upperRight(vector2NegativeCoordinates);
        Vector2d resultZeroCoordinates = vectorZeroCoordinates.upperRight(vector1PositiveCoordinates);
        Vector2d resultTheSameCoordinates = vector1TheSameCoordinates.upperRight(vector2TheSameCoordinates);
        Vector2d resultMixedCoordinates = vector1MixedCoordinates.upperRight(vector2MixedCoordinates);

        // then
        assertEquals(new Vector2d(3, 2), resultPositiveCoordinates);
        assertEquals(new Vector2d(-2, -3), resultNegativeCoordinates);
        assertEquals(new Vector2d(1, 2), resultZeroCoordinates);
        assertEquals(new Vector2d(7, 8), resultTheSameCoordinates);
        assertEquals(new Vector2d(2, 4), resultMixedCoordinates);
    }

    @Test
    void testLowerLeftMethod() {
        // given
        Vector2d vector1PositiveCoordinates = new Vector2d(1, 2);
        Vector2d vector2PositiveCoordinates = new Vector2d(3, 1);
        Vector2d vector1NegativeCoordinates = new Vector2d(-4, -3);
        Vector2d vector2NegativeCoordinates = new Vector2d(-2, -5);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vector1TheSameCoordinates = new Vector2d(7, 8);
        Vector2d vector2TheSameCoordinates = new Vector2d(7, 8);
        Vector2d vector1MixedCoordinates = new Vector2d(-1, 4);
        Vector2d vector2MixedCoordinates = new Vector2d(2, -3);

        // when
        Vector2d resultPositiveCoordinates = vector1PositiveCoordinates.lowerLeft(vector2PositiveCoordinates);
        Vector2d resultNegativeCoordinates = vector1NegativeCoordinates.lowerLeft(vector2NegativeCoordinates);
        Vector2d resultZeroCoordinates = vectorZeroCoordinates.lowerLeft(vector1PositiveCoordinates);
        Vector2d resultTheSameCoordinates = vector1TheSameCoordinates.lowerLeft(vector2TheSameCoordinates);
        Vector2d resultMixedCoordinates = vector1MixedCoordinates.lowerLeft(vector2MixedCoordinates);

        // then
        assertEquals(new Vector2d(1, 1), resultPositiveCoordinates);
        assertEquals(new Vector2d(-4, -5), resultNegativeCoordinates);
        assertEquals(new Vector2d(0, 0), resultZeroCoordinates);
        assertEquals(new Vector2d(7, 8), resultTheSameCoordinates);
        assertEquals(new Vector2d(-1, -3), resultMixedCoordinates);
    }

    @Test
    void testAddMethod() {
        // given
        Vector2d vector1PositiveCoordinates = new Vector2d(1, 2);
        Vector2d vector2PositiveCoordinates = new Vector2d(3, 1);
        Vector2d vector1NegativeCoordinates = new Vector2d(-4, -3);
        Vector2d vector2NegativeCoordinates = new Vector2d(-2, -5);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vectorMixedCoordinates = new Vector2d(-1, 4);

        // when
        Vector2d resultPositiveCoordinates = vector1PositiveCoordinates.add(vector2PositiveCoordinates);
        Vector2d resultNegativeCoordinates = vector1NegativeCoordinates.add(vector2NegativeCoordinates);
        Vector2d resultZeroCoordinates = vectorZeroCoordinates.add(vector1PositiveCoordinates);
        Vector2d resultMixedCoordinates = vectorMixedCoordinates.add(vector1PositiveCoordinates);

        // then
        assertEquals(new Vector2d(4, 3), resultPositiveCoordinates);
        assertEquals(new Vector2d(-6, -8), resultNegativeCoordinates);
        assertEquals(new Vector2d(1, 2), resultZeroCoordinates);
        assertEquals(new Vector2d(0, 6), resultMixedCoordinates);
    }

    @Test
    void testSubtractMethod() {
        // given
        Vector2d vector1PositiveCoordinates = new Vector2d(1, 2);
        Vector2d vector2PositiveCoordinates = new Vector2d(3, 1);
        Vector2d vector1NegativeCoordinates = new Vector2d(-4, -3);
        Vector2d vector2NegativeCoordinates = new Vector2d(-2, -5);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vectorMixedCoordinates = new Vector2d(-1, 4);

        // when
        Vector2d resultPositiveCoordinates = vector1PositiveCoordinates.subtract(vector2PositiveCoordinates);
        Vector2d resultNegativeCoordinates = vector1NegativeCoordinates.subtract(vector2NegativeCoordinates);
        Vector2d resultZeroCoordinates = vectorZeroCoordinates.subtract(vector1PositiveCoordinates);
        Vector2d resultMixedCoordinates = vectorMixedCoordinates.subtract(vector1PositiveCoordinates);

        // then
        assertEquals(new Vector2d(-2, 1), resultPositiveCoordinates);
        assertEquals(new Vector2d(-2, 2), resultNegativeCoordinates);
        assertEquals(new Vector2d(-1, -2), resultZeroCoordinates);
        assertEquals(new Vector2d(-2, 2), resultMixedCoordinates);
    }

    @Test
    void testOppositeMethod() {
        // when
        Vector2d vectorPositiveCoordinates = new Vector2d(1, 2);
        Vector2d vectorNegativeCoordinates = new Vector2d(-4, -3);
        Vector2d vectorZeroCoordinates = new Vector2d(0, 0);
        Vector2d vectorMixedCoordinates = new Vector2d(-1, 4);

        // then
        assertEquals(new Vector2d(-1, -2), vectorPositiveCoordinates.opposite());
        assertEquals(new Vector2d(4, 3), vectorNegativeCoordinates.opposite());
        assertEquals(new Vector2d(0, 0), vectorZeroCoordinates.opposite());
        assertEquals(new Vector2d(1, -4), vectorMixedCoordinates.opposite());
    }
}