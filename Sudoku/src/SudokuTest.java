import java.util.Scanner;

/**
 *author: Ulemj
 * Sudoku: puzzle that consists of a 9x9 grid of cells.
 * Objective of the game: fill in every blank cell with a number from 1 to 9 such that
 * every digit from 1 to 9 is present in every row, every column, and every 3x3 box
 *
 * This code will accept two files (one optional).
 * File #1 will have the board that needs to be solved
 * File #2 will be the solution
 *
 * It will generate the solved board and check to see whether the solution generated matches the solution provided
 * This class holds the main method that asks the user for file input, creates SudokuPuzzle's, and generates the output
 */
public class SudokuTest {
    private static Scanner scan;
    public static void main(String[] args) {
        scan = new Scanner(System.in);

        //asking and storing 1st line (for puzzle board)
        System.out.println("Enter filename of puzzle: ");
        String filename = scan.nextLine();

        //asks and stores second line of user input for solved file. this is optional
        System.out.println("Enter filename of solution (optional): ");
        String filenameTwo = scan.nextLine();

        //create a SudokuPuzzle object with filename
        SudokuPuzzle one = new SudokuPuzzle(filename);

        //prints the unsolved puzzle
        System.out.println("Starting puzzle: ");
        System.out.println(one);

        //solves the puzzle
        SudokuSolver three = new SudokuSolver(one);
        three.solve(one);

        //prints solved puzzle
        System.out.println("Solved Puzzle: ");
        System.out.println(one);

        //checks to see whether filenameTwo is blank, if so it indicates so
        //else it creates a puzzle board for solution board
        //calls equals method to see if solved solution is the same as provided solution
        if (filenameTwo.equals("")) {
            System.out.println("no solution file");
        }
        else{
            SudokuPuzzle two = new SudokuPuzzle(filenameTwo);
            if (one.equals(two)) {
                System.out.println("Solution is correct!");
            } else {
                System.out.println("Not correct");
            }
        }
    }
}
