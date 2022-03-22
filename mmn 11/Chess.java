/**
 * This program reads two chessmen's locations on a chessboard
 * and checks if one of the chessmen threats the other
 *
 * @author Omer Siman Tov
 * @version 13/03/2022
 */

import java.util.Scanner;
public class Chess
{
    public static void main (String[] args)
    {
        // Define the min & max row & column numbers based on the chessboard
        final int MIN_ROW = 1, MAX_ROW = 8, MIN_COL = 1, MAX_COL = 8;

        // Request and read the chessmen types and the chessmen positions from the user
        Scanner scan = new Scanner (System.in);
        System.out.println("Please enter the type"+
            " of the first chessman");
        char first = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row1 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col1 = scan.nextInt();
        System.out.println("Please enter the type"+
            " of the second chessman");
        char second = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row2 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col2 = scan.nextInt();

        // Check if the first & second chessmen are of the same type
        if (first == second)
            System.out.println("Chessmen should be different from each other");

        // Check if one of the positions inserted is illegal
        else if (row1 < MIN_ROW || row1 > MAX_ROW || row2 < MIN_ROW || row2 > MAX_ROW || 
        col1 < MIN_COL || col1 > MAX_COL || col2 < MIN_COL || col2 > MAX_COL)
            System.out.println("Position is not legal");

        // Check if the positions inserted are identical
        else if (row1 == row2 && col1 == col2)
            System.out.println("Chessmen positions should not be identical");

        else {
            // Input is valid, check for threats

            boolean isThreat = false; // A varible to keep track of is there a threat or not

            if (first == 'k' || second == 'k') {
                // If one of the chessmen is a knight, check for knight threats
                if (row1 - 1 == row2 && col1 + 2 == col2 || row1 - 1 == row2 && col1 - 2 == col2
                || row1 + 1 == row2 && col1 + 2 == col2 || row1 + 1 == row2 && col1 - 2 == col2
                || row1 - 2 == row2 && col1 + 1 == col2 || row1 - 2 == row2 && col1 - 1 == col2
                || row1 + 2 == row2 && col1 + 1 == col2 || row1 + 2 == row2 && col1 - 1 == col2) {
                    isThreat = true;

                    if (first == 'b' || second == 'b')
                        System.out.println("knight threats bishop");
                    else if (first == 'r' || second == 'r')
                        System.out.println("knight threats rook");
                }
            }

            if (first == 'b' || second == 'b') {
                // If one of the chessmen is a bishop, check for bishop threats
                if (row1 - 1 == row2 && col1 - 1 == col2 ||
                row1 - 2 == row2 && col1 - 2 == col2 ||
                row1 - 3 == row2 && col1 - 3 == col2 ||
                row1 - 4 == row2 && col1 - 4 == col2 ||
                row1 - 5 == row2 && col1 - 5 == col2 ||
                row1 - 6 == row2 && col1 - 6 == col2 ||
                row1 - 7 == row2 && col1 - 7 == col2 ||
                row1 + 1 == row2 && col1 + 1 == col2 ||
                row1 + 2 == row2 && col1 + 2 == col2 ||
                row1 + 3 == row2 && col1 + 3 == col2 ||
                row1 + 4 == row2 && col1 + 4 == col2 ||
                row1 + 5 == row2 && col1 + 5 == col2 ||
                row1 + 6 == row2 && col1 + 6 == col2 ||
                row1 + 7 == row2 && col1 + 7 == col2) {
                    isThreat = true;

                    if (first == 'r' || second == 'r')
                        System.out.println("bishop threats rook");
                    else if (first == 'k' || second == 'k')
                        System.out.println("bishop threats knight");
                }
            }

            if (first == 'r' || second == 'r') {
                // If one of the chessmen is a rook, check for rook threats
                if (row1 == row2 || col1 == col2) {
                    isThreat = true;

                    if (first == 'b' || second == 'b')
                        System.out.println("rook threats bishop");
                    else if (first == 'k' || second == 'k')
                        System.out.println("rook threats knight");
                }
            }

            // Check if a threat was found or not
            if (!isThreat)
                System.out.println("no threat");
        }
    } // end of method main
} // end of class Chess