package projects.Maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordsTest {
    

    @Test
    void testCoordsEquals(){
        Coords coord1 = new Coords(1,4);
        Coords coord2 = new Coords(1,4);
        Coords coord3 = new Coords(1,2);
        Coords coord4 = new Coords(8,4);
        Coords coord5 = new Coords(5,7);
        assertTrue(coord1.equals(coord2));
        assertFalse(coord1.equals(coord3));
        assertFalse(coord1.equals(coord5));
        assertFalse(coord1.equals(coord4));
    }
}
