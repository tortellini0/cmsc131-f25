package projects.Maze;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    Maze maze;
    @BeforeEach
    /**
     *     0 1 2
     *   -------
     * 0 | X S X
     * 1 | O O O
     * 2 | X O X
     * 3 | X O X
     * 4 | X E X
     */
    void setup(){
        maze = new Maze(7);
        maze.insertCell(new Cell(new Coords(0,1),CellStatus.S));
        maze.insertCell(new Cell(new Coords(1,1),CellStatus.O));
        maze.insertCell(new Cell(new Coords(1,2),CellStatus.O));
        maze.insertCell(new Cell(new Coords(1,0),CellStatus.O));
        maze.insertCell(new Cell(new Coords(2,1),CellStatus.O));
        maze.insertCell(new Cell(new Coords(3,1),CellStatus.O));
        maze.insertCell(new Cell(new Coords(4,1),CellStatus.E));
    }

    @Test
    void testCheckOrthogonal(){
        Grid grid1 = maze.getGrid();
        Cell[] cellList = grid1.getAllCells();
        assertTrue(maze.checkOrthogonal(cellList[0], cellList[1]));
        assertFalse(maze.checkOrthogonal(cellList[0], cellList[6]));
    }
    
  
}
