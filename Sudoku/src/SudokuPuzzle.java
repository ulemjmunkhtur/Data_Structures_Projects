import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Takes in a filename and creates a Sudoku puzzle
 * Holds methods that helps read the file and solve the puzzle:
 * readPuzzle(),isValidMove(), nextValidMove(), setValue(), getValue(), equals()
 * This class holds the SudokuPuzzle at all stages (not solved, fully solved)
 */
public class SudokuPuzzle {

    private static final int NUM_COL = 9;
    private final static int NUM_ROW = 9;
    private int[][] sudokuBoard = new int[NUM_ROW][NUM_COL];
    private static Scanner scan;
    /**
     *Constructor for SudokuPuzzle class
     * Calls readPuzzle() method and constructs SudokuBoard object.
     * @param filename accepts string file name
     */
    public SudokuPuzzle(String filename){
        readPuzzle(filename);
    }

    /**
     * Takes in a string, converts it to a file, scans it and reads each int to set sudokuBoard value
     * catches if filename is invalid (with FileNotFoundException)
     * @param filename (string)
     */
    public void readPuzzle(String filename) {
        try {
            scan = new Scanner(new File(filename));
            while (scan.hasNext()) {
                for (int i = 0; i < NUM_ROW; i++) {
                    for (int j = 0; j < NUM_COL; j++) {
                        sudokuBoard[i][j] = scan.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("error in reading file");
        }
    }

    /**
     * toString for SudokuPuzzle. represents 2D array SudokuBoard in a grid.
     * Replaces all 0 (that are meant to represent gaps) with "_"
     * @return board
     * ex. 5 _ _ _ _ 9 7 6 _
     * _ _ 4 _ 8 _ _ 1 _
     * _ _ 2 6 _ _ _ 9 _
     * _ _ _ _ _ 8 _ _ _
     */
    public String toString() {
        String s = "";
        for (int row = 0; row < NUM_ROW; row++) {
            for (int column = 0; column < NUM_COL; column++) {
                if (sudokuBoard[row][column] != 0) {
                    s += sudokuBoard[row][column] + " ";
                } else {
                    s += "_ ";
                }
            }
            s += "\n";

        }
        return s;

    }

    /**
     * Checks the values in the move's row, column, and 3x3 box to see if there is the same value as the move
     * @param row (int) row that you want to test for
     * @param col (int) column that you want to test for
     * @param move (int) value that you want to test the position for
     * @return bool
     * false: if the value isn't valid (if it appears in the same row, col or box)
     * true: if the value is unique to the row, col, or box
     */
    public boolean isValidMove(int row, int col, int move) {
        // Check for duplicate values in same row
        for (int i = 0; i < NUM_COL; i++) {
            if (sudokuBoard[row][i] == move) {
                return false;
            }
        }
        // Check for duplicate values in same column
        for (int i = 0; i < NUM_ROW; i++) {
            if (sudokuBoard[i][col] == move) {
                return false;
            }
        }
        int boxRow = (row / 3);
        int boxCol = (col / 3);
        // Check for duplicate values in same 3x3 subgrid
        for (int i = boxRow * 3; i < (boxRow * 3) + 3; i++) {
            for (int j = boxCol * 3; j < (boxCol * 3) + 3; j++) {
                if (sudokuBoard[i][j] == move) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Gets the next valid move based on the prior (valid) move that was in that spot
     *
     * @param row (int) row that you want to test for
     * @param col (int) column that you want to test for
     * @param priorMove (int) value that you want to test the position for
     * @return createMove (of SudokuMove type) which is the nextValidMove in said position
     * null: if there isn't another valid move in the position.
     */
    public SudokuMove nextValidMove(int row, int col, int priorMove) {
        //loops through possible moves starting from priorMove+1 (so it doesn't count priorMove again)

        for (int i = priorMove + 1; i < 10; i++) {
            SudokuMove createMove = new SudokuMove(row, col, i);
            if (isValidMove(row, col, i)) {
                return createMove;
            }
        }
        return null;
    }

    /**
     * allow you to check whether two puzzles match
     * e.g., your solved puzzle and a known solution to the puzzle
     * @param two (SudokuPuzzle) would most likely be the solution of the file
     * @return (bool)
     * true: if each value in both boards match (if they are identical)
     * false: if the two boards aren't identical
     */
    public boolean equals(SudokuPuzzle two) {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                if (sudokuBoard[i][j] != two.getValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This sets the value of the board's row and column to move
     *@param row (int) row that you want to set a value for
      * @param col (int) column that you want to set a value for
     * @param move (int) value that you want to set the position to
     */
    public void setValue(int row, int col, int move){
        sudokuBoard[row][col] = move;
    }

    /**
     * lets other classes look at the values of given positions
     * @param row (int) row that you want to get the value for
     * @param column (int) column that you want to get the value for
     * @return (int) the value of the board at the given row and column
     */
    public int getValue(int row, int column){
        return sudokuBoard[row][column];
    }
}