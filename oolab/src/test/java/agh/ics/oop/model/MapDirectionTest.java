package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testNextMethod() {
        // when
        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;

        // then
        assertEquals(e, n.next());
        assertEquals(s, e.next());
        assertEquals(w, s.next());
        assertEquals(n, w.next());
    }

    @Test
    void testPreviousMethod() {
        // when
        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;

        // then
        assertEquals(n.previous(), w);
        assertEquals(e.previous(), n);
        assertEquals(s.previous(), e);
        assertEquals(w.previous(), s);
    }
}