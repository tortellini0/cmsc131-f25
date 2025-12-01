/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

package projects.Maze;

public class Cell {

    private final Coords coords;
    private Coords[] neighbors;
    private boolean explored;
    private CellStatus status;
    private int neighborCount;
    public Cell(Coords c, CellStatus s) {
        coords = c;
        neighbors = new Coords[4];
        status = s;
        explored = false;
        neighborCount = 0;
    }

    // accessor methods
    public CellStatus getStatus(){
        return status;
    }

    public Coords[] getNeighbors(){
        return neighbors;
    }

    public boolean getExplored(){
        return explored;
    }

    public Coords getCoords() {
        return coords;
    }

    public int getNeighborCount(){
        return neighborCount;
    }
     
    public void explore(){
        explored = true;
    }
    /**
     * changes the status of a cell
     * @param c - CellStatus - status that the cell is being changed to
     */
    public void changeStatus(CellStatus c){
        status = c;
    }
    /**
     * adds a neighbor Coords to the neighbors array
     * @param coord - Coords - coord that is being added to the array
     */
    public boolean addNeighbor(Coords coord){
        if(coord == null){
            throw new IllegalArgumentException("coord cant be null");
        }
        if (neighborCount < 4){
            neighbors[neighborCount] = coord;
            neighborCount++;
            return true;
        }
        return false;
    }
    

}
