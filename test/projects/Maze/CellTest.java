package projects.Maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class CellTest {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;

    @BeforeEach
    void setup(){
        cell1 = new Cell(new Coords(1,1),CellStatus.O);
        cell2 = new Cell(new Coords(1,2),CellStatus.O);
        cell3 = new Cell(new Coords(1,1),CellStatus.S);
        cell4 = new Cell(new Coords(1,1),CellStatus.E);
    }

    @Test
    void addNeighborThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {cell1.addNeighbor(null);}
        );
        assertEquals(
            "coord cant be null",
            exception.getMessage()
        );
    }

    @Test
    void testStatusMethods(){
        assertEquals(CellStatus.O,cell1.getStatus());
        cell1.changeStatus(CellStatus.P);
        assertEquals(CellStatus.P,cell1.getStatus());
    }

    @Test
    void testGetCoords(){
        assertTrue(cell1.getCoords().equals(new Coords(1,1)));
    }
    
    @Test
    void testExploreMethods(){    
        assertFalse(cell1.getExplored());
        cell1.explore();
        assertTrue(cell1.getExplored());
    }

    @Test
    void testAddNeighborsTrueAndFalse(){
        assertTrue(cell1.addNeighbor(new Coords(1,2)));
        assertTrue(cell1.addNeighbor(new Coords(1,0)));
        assertTrue(cell1.addNeighbor(new Coords(2,1)));
        assertTrue(cell1.addNeighbor(new Coords(0,1)));
        assertFalse(cell1.addNeighbor(new Coords(0, 0)));
    }

    @Test
    void testNeighborsMethods(){
        Coords[] neighborsTemp = new Coords[4];
        neighborsTemp = cell1.getNeighbors();
        Coords[] expectedNeighbors = new Coords[4];
        assertEquals(0,cell1.getNeighborCount());
        
        for (int i = 0; i < 4; i++){
            assertEquals(expectedNeighbors[i],neighborsTemp[i]);
        }

        cell1.addNeighbor(new Coords(1,2));
        neighborsTemp = cell1.getNeighbors();
        expectedNeighbors[0] = new Coords(1,2);
        assertEquals(1,cell1.getNeighborCount());
        
        int n = 0;
        for (Coords coord : expectedNeighbors){
            if(coord != null){
                assertTrue(coord.equals(neighborsTemp[n]));
                n++;
            }
        }

    }
}
