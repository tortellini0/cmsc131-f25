package projects.Maze;

public class Coords {

    private final int row;
    private final int col;

    public Coords(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    /**
     * tests if two Coords are referencing the same row and col position
     * @param other - Coords - other coord being compared to
     * @return - True for same position referenced - False for different position referenced
     */
    public boolean equals(Coords other) {
        if (other == null){
            throw new IllegalArgumentException("other cant be null");
        }
        return (
            getRow() == other.getRow()
            && getCol() == other.getCol()
        );
    }

}
