/** Maze class specification

- `Maze(int maxCells)` - pre-allocates array to maximum size

- `getStart()` - finds and returns the cell with Status Start

- `getEnd()` - finds and returns the cell with Status End
    - Consider writing a helper method `getFirstCellWithStatus(Status)` which does linear search

- setupNeighbors() populates the neighbors list of each cell in the grid

 */

package projects.Maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Maze {

    private final Grid grid;
    
    public Maze(int maxCells) {
        grid = new Grid(maxCells);
    }

    public Grid getGrid(){
        return grid;
    }

    public Cell getEnd(){
        Cell end = grid.getFirstCellWithStatus(CellStatus.E);
        return end;
    }

    public Cell getStart(){
        Cell start = grid.getFirstCellWithStatus(CellStatus.S);
        return start;
    }

    public void insertCell(Cell cell){
        grid.insertCell(cell);
    }
    public void discoverAndSetupNeighbors() {
        Cell[] cellList = grid.getAllCells();
        for (int i = 0; i < cellList.length; i++){
            Cell targetCell = cellList[i];
            
            for(int n = 0; n < cellList.length; n++){
                if(n != i){
                    Cell tempNeighborCell = cellList[n];
                    if(checkOrthogonal(targetCell, tempNeighborCell)){
                        targetCell.addNeighbor(tempNeighborCell.getCoords());
                    }
                }
            }
        }
    }
    
    public boolean checkOrthogonal(Cell cell1, Cell cell2){
        int horizontalDifference = Math.abs(cell1.getCoords().getRow()-cell2.getCoords().getRow());
        int verticalDifference = Math.abs(cell1.getCoords().getCol()-cell2.getCoords().getCol());
        if(horizontalDifference + verticalDifference == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Provided by Dusel. Assumes grid cell has a getStatus() method.
     * @param filename - Output filename.
     */
    public void serialize(String filename) {
        Cell[] cells = grid.getAllCells();

        FileWriter writer;
        try {
            writer = new FileWriter(new File(filename));
            // discover dimension of maze's underlying grid
            int maxRow = 0, maxCol = 0;
            int idxCell;
            for (idxCell = 0; idxCell < cells.length; idxCell++) {
                int row = cells[idxCell].getCoords().getRow();
                int col = cells[idxCell].getCoords().getCol();
                if (row > maxRow) { maxRow = row; }
                if (col > maxCol) { maxCol = col; }
            }
    
            // write cell statuses, using 'X' for absent (inaccessible) cells
            idxCell = 0;
            for (int row = 0; row <= maxRow; row++) {
                for (int col = 0; col <= maxCol; col++) {
                    Cell maybeCell = grid.getCell(
                        new Coords(row, col)
                    );
                    if (maybeCell != null) {
                        writer.write(maybeCell.getStatus().name());
                        idxCell++;
                    } else {
                        writer.write('X');
                    }
                    writer.write(' ');
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
