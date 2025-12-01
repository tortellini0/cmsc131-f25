package projects.Maze;

public class Grid {

    private final Cell[] cells;
    private int cellCount;

    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }

    public Cell getFirstCellWithStatus(CellStatus c){
        for (int i = 0; i < getAllCells().length; i++){
            if (cells[i].getStatus() == c){
                return cells[i];
            }
            
        }
        return null;
    }

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

    public Cell getCell(Coords vh) {
        for (int idx = 0; idx < cellCount; idx++) {
            if ( cells[idx].getCoords().equals(vh) ) {
               return cells[idx];
            }
        }
        return null;
    }// TODO test coords in grid, then test return null

    public int getCellCount() {
        return cellCount;
    }

    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }
}
