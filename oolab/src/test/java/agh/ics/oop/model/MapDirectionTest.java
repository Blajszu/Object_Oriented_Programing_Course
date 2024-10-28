package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    final MapDirection N = MapDirection.NORTH;
    final MapDirection E = MapDirection.EAST;
    final MapDirection S = MapDirection.SOUTH;
    final MapDirection W = MapDirection.WEST;

    @Test
    void testNextMethod() {
        assertEquals(E, N.next());
        assertEquals(S, E.next());
        assertEquals(W, S.next());
        assertEquals(N, W.next());
    }

    @Test
    void testPreviousMethod() {
        assertEquals(N.previous(), W);
        assertEquals(E.previous(), N);
        assertEquals(S.previous(), E);
        assertEquals(W.previous(), S);
    }
}