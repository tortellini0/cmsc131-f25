package projects.Maze;

public class Grid {

    private final Cell[] cells;
    private int cellCount;

    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }
    
    public int getCellCount() {
        return cellCount;
    }
    
    /**
     * gets the first cell in the array that has the same status as the one you are finding
     * @param c - CellStatus - status you are looking for
     * @return - first cell with the status you are looking for
     */
    public Cell getFirstCellWithStatus(CellStatus c){
        for (int i = 0; i < getAllCells().length; i++){
            if (cells[i].getStatus() == c){
                return cells[i];
            }
            
        }
        return null;
    }
    /**
     * inserts a cell into the array of cells
     * @param cell - Cell - Cell that you are inserting into the array
     * @return - true for inserted - false for failed insert
     */
    public boolean insertCell(Cell cell) {
        if(cell == null){
            throw new IllegalArgumentException("cell cant be null");
        }
        if (cellCount < cells.length) {
            cells[cellCount] = cell;
            cellCount++;
            return true;
        }
        return false;
    }
    /**
     * gets the cell with a specified coordinate
     * @param vh - Coords - coords to find the cell
     * @return - cell with the sane coords as vh
     */
    public Cell getCell(Coords vh) {
        for (int idx = 0; idx < cellCount; idx++) {
            if ( cells[idx].getCoords().equals(vh) ) {
               return cells[idx];
            }
        }
        return null;
    }

    /**
     * returns an array of all of the cells
     * @return - cell[] - array of all cells
     */
    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }
}
