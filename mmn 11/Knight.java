/**
 * This program reads a knight's location on a
 * chessboard and prints all of its legal moves
 *
 * @author Omer Siman Tov
 * @version 13/03/2022
 */

import java.util.Scanner;
public class Knight
{
    public static void main (String[] args)
    {
        // Define the min & max row & column numbers based on the chessboard
        final int MIN_ROW = 1, MAX_ROW = 8, MIN_COL = 1, MAX_COL = 8;

        // Request and read row & column numbers from the user
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program reads two integers which " +
            "represent the knight's location on the chess board: ");
        System.out.println ("Please enter the number of row");
        int row = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col = scan.nextInt();

        // Check if the input scanned is out of the legal range
        if (row < MIN_ROW || row > MAX_ROW || col < MIN_COL || col > MAX_COL)
            System.out.println("input is illegal");

        else {
            // Input is legal, print all of the Knight's legal moves
            System.out.println("Moves: ");

            if (row - 1 >= MIN_ROW && col + 2 <= MAX_COL)
                System.out.println((row - 1) + " " + (col + 2));

            if (row - 1 >= MIN_ROW && col - 2 >= MIN_COL)
                System.out.println((row - 1) + " " + (col - 2));

            if (row + 1 <= MAX_ROW && col + 2 <= MAX_COL)
                System.out.println((row + 1) + " " + (col + 2));

            if (row + 1 <= MAX_ROW && col - 2 >= MIN_COL)
                System.out.println((row + 1) + " " + (col - 2));

            if (row - 2 >= MIN_ROW && col + 1 <= MAX_COL)
                System.out.println((row - 2) + " " + (col + 1));

            if (row - 2 >= MIN_ROW && col - 1 >= MIN_COL)
                System.out.println((row - 2) + " " + (col - 1));

            if (row + 2 <= MAX_ROW && col + 1 <= MAX_COL)
                System.out.println((row + 2) + " " + (col + 1));

            if (row + 2 <= MAX_ROW && col - 1 >= MIN_COL)
                System.out.println((row + 2) + " " + (col - 1));
        }
    } // end of method main
} // end of class Knight