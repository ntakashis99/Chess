package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    //Private variables are row and col
    private final int row;
    private final int col;

    public ChessPosition(int row, int col) {
        if(row < 1 || row > 8 || col < 0 || col > 8){
            throw new IllegalArgumentException("This position is invalid, number should be between 1 and 8");
        }
        this.row = row;
        this.col = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {

        return this.row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return this.col;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.row, this.col);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        ChessPosition that = (ChessPosition) o;
        return (row == that.row) && (col == that.col);
    }

    @Override
    public int hashCode() {
        int code = 23 * row;
        code = 23 * code + col;
        return code;
    }
}
