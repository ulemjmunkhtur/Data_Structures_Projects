/**
 * SudokuMove contains an object representing a single digit placement while solving a Sudoku puzzle
 * ex. the placement of the digit 8 in row 3, column 5 of the grid
 */
public class SudokuMove {
    private final int row;
    private final int col;
    private final int move;

    /**
     * Constructor of class. Initializes and stores move information
     * @param row int that identifies row number
     * @param col int that identifies column number
     * @param move int that represents value to be placed at specified row and column
     */
    public SudokuMove(int row, int col, int move){
        this.row = row;
        this.col = col;
        this.move = move;

    }

    /**
     * getter method for row
     * @return which row a move is at (int)
     */

    public int getRow() {
        return row;
    }

    /**
     *getter method for columns
     *@return which column a move is at (int)
     */
    public int getCol() {
        return col;
    }

    /**
     *getter method for move
     * @return the value of the move that is placed in that spot (int)
     */
    public int getMove() {
        return move;
    }
}
