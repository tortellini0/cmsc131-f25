package projects.Maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {
    Grid grid1;
    @BeforeEach
    void setup(){
        grid1 = new Grid(5);
        grid1.insertCell(new Cell(new Coords(0,1),CellStatus.S));
        grid1.insertCell(new Cell(new Coords(1,1),CellStatus.O));
        grid1.insertCell(new Cell(new Coords(2,1),CellStatus.O));
        grid1.insertCell(new Cell(new Coords(3,1),CellStatus.O));
        grid1.insertCell(new Cell(new Coords(4,1),CellStatus.O));
    }

    @Test
    public void testInsertAndRetrieveFirstCell() {
        Grid grid = new Grid(10);
        Coords coords = new Coords(0, 0);
        Cell cell = new Cell(coords, CellStatus.O);
        
        grid.insertCell(cell);
        
        Cell retrieved = grid.getCell(coords);
        assertNotNull(retrieved);
    }

    @Test
    public void testCellCountAfterInsert() {
        Grid grid = new Grid(10);
        Cell cell1 = new Cell(new Coords(0, 0), CellStatus.O);
        Cell cell2 = new Cell(new Coords(1, 1), CellStatus.O);
        
        grid.insertCell(cell1);
        assertEquals(1, grid.getCellCount());
        
        grid.insertCell(cell2);
        assertEquals(2, grid.getCellCount());
    }

    @Test
    public void testGetAllCellsReturnsCorrectCount() {
        Grid grid = new Grid(10);
        grid.insertCell(new Cell(new Coords(0, 0), CellStatus.O));
        grid.insertCell(new Cell(new Coords(1, 1), CellStatus.O));
        grid.insertCell(new Cell(new Coords(2, 2), CellStatus.O));
        
        Cell[] allCells = grid.getAllCells();
        assertEquals(3, allCells.length);
        
        for (int i = 0; i < allCells.length; i++) {
            assertNotNull(allCells[i]);
        }
    }

    @Test
    public void testInsertAtCapacityBoundary() {
        Grid grid = new Grid(3);
        
        assertTrue(grid.insertCell(new Cell(new Coords(0, 0), CellStatus.O)));
        assertTrue(grid.insertCell(new Cell(new Coords(1, 1), CellStatus.O)));
        assertTrue(grid.insertCell(new Cell(new Coords(2, 2), CellStatus.O)));
        assertFalse(grid.insertCell(new Cell(new Coords(3, 3), CellStatus.O)));
    }

    @Test
    void incertCellThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {grid1.insertCell(null);}
        );
        assertEquals(
            "cell cant be null",
            exception.getMessage()
        );
    }

    @Test
    void testGetCell(){
        Coords tempCoord = new Coords(0, 1);
        assertTrue(tempCoord.equals(grid1.getCell(tempCoord).getCoords()));
        Coords nullCoord = new Coords(100,100);
        assertNull(grid1.getCell(nullCoord));
    }

}
