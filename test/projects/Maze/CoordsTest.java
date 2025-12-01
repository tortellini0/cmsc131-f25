package projects.Maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordsTest {
    Coords coord1;
    Coords coord2;
    Coords coord3;
    Coords coord4;
    Coords coord5;
    @BeforeEach
    void setup(){
        
        coord1 = new Coords(1,4);
        coord2 = new Coords(1,4);
        coord3 = new Coords(1,2);
        coord4 = new Coords(8,4);
        coord5 = new Coords(5,7);
    }

    @Test
    void addNeighborThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {coord1.equals(null);}
        );
        assertEquals(
            "other cant be null",
            exception.getMessage()
        );
    }


    @Test
    void testCoordsEquals(){
        assertTrue(coord1.equals(coord2));
        assertFalse(coord1.equals(coord3));
        assertFalse(coord1.equals(coord5));
        assertFalse(coord1.equals(coord4));
    }
    @Test
    void testAccesors(){
        assertEquals(8, coord4.getRow());
        assertEquals(4, coord4.getCol());
    }
}
