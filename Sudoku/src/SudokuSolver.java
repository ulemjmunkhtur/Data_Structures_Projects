import java.util.LinkedList;

/**
 * Class that can solve a given SudokuPuzzle.
 * Checks to see whether there is a 0 in the SudokuPuzzle and uses isValidMove() and nextValidMove() from SudokuPuzzle
 * Holds methods: solve(), isItSolved()
 */
public class SudokuSolver {
    private SudokuPuzzle board;

    private static final int NUM_COL = 9;
    private static final int NUM_ROW = 9;

    private LinkedList<SudokuMove> moves = new LinkedList();

    /**
     * Constructor for SudokuPuzzle class
     * @param board of SudokuPuzzle type
     */
    public SudokuSolver(SudokuPuzzle board) {
        this.board = board;
    }

    /**
     * Solves the SudokuBoard that is given
     * looks for 0 (meaning a move must be placed) in every square on the board. iterates through possible moves (1-9)
     * to place value if move is valid
     * if it gets to a point where the move is invalid, then it begins backtracking
     * will use .setValue() in order to update the SudokuPuzzle class contents as it goes
     * after implementing solve(), if you call the SudokuPuzzle it will print a solved puzzle
     * @param board of SudokuBoard type
     */
    public void solve(SudokuPuzzle board) {
        int row = 0;
        //checks to see if board is solved or not
        if (!isItSolved()) {
            //loops through each row
            while (row < NUM_ROW) {
                int col = 0;
                //loops through each column
                while (col < NUM_COL) {
                    if (board.getValue(row, col) == 0) {
                        int move = 0;
                        //creates the next valid move
                        SudokuMove currMove = board.nextValidMove(row, col, move);
                        if (currMove == null) {
                            //begins backtracking
                            while (currMove == null) {

                                SudokuMove prevMove = moves.pop();

                                //sets the value of the move as you go back to 0
                                board.setValue(prevMove.getRow(), prevMove.getCol(), 0);

                                //generates the next valid move in that space
                                currMove = board.nextValidMove(prevMove.getRow(), prevMove.getCol(), prevMove.getMove());

                                //sets the row and column to previous move's row and column
                                row = prevMove.getRow();
                                col = prevMove.getCol();
                            }
                        }
                        //adds the move to the stack
                        moves.push(currMove);
                        board.setValue(currMove.getRow(), currMove.getCol(), currMove.getMove());
                    }
                    col++;
                }
                row++;
            }

        }
    }

    /**
     * Checks whether the board has been solved or not
     * @return bool
     * true: if all the values are not 0
     * false: if any one of the values are 0
     */
    public boolean isItSolved() {
        for (int rows = 0; rows < NUM_ROW; rows++) {
            for (int column = 0; column < NUM_COL; column++) {
                if (board.getValue(rows, column) == 0) {
                    return false;
                }

            }

        }
        return true;

    }
}




