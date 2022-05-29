/**
 * mmn 14 — Complexity & Recursion
 *
 * @author Omer Siman Tov
 * @version 22/5/2022
 */

    /**
     * 
     * Question 1.a
     * 
     * Out of the 6 statements stated in the question, the following are correct: 1, 3, 5, 6.
     *
     */

public class Ex14 {
    /**
     * 
     * Question 1.b.1
     * 
     * This method checks if a given integer exists inside a matrix in which each row is sorted (row cells go up in value).
     *
     * Time complexity:
     * O(n)— The number of times the while loop is executed is directly and linearly correlated to the size of the matrix.
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param m The matrix to search for val inside.
     * @param val The value to check if exists inside the matrix.
     * 
     * @return True if val exists inside m or false if not.
     *
     */

    public static boolean findValWhat(int[][] m, int val) {
        /* Define initial row & col indexes for the upcoming search, 
        start from the top right and move in accordance to where val should be found based on the sorted structure of m */
        int row = 0, col = m[0].length - 1;

        /* While we haven't passed the bottom row of the matrix — check if the current cell's value is equal to val,
        if so, return true, if not — check if val is larger than the current cell's value, if so, go down one cell, 
        if not — check if val is smaller than the current cell's value, if so, go left one one cell. */
        while (row < m.length) {
            if (val == m[row][col]) return true;
            else if (val > m[row][col]) row++;
            else if (val < m[row][col]) col--;
        }

        // Return false if val was not found in any cell inside the matrix.
        return false;
    }

    /**
     * 
     * Question 1.b.2
     * 
     * This method checks if a given integer exists inside a completely sorted matrix (each cell goes up in value).
     *
     * Time complexity:
     * O(n) — The method 'search' executes binary search and therefore has a time complexity of O(logn). 
     * The array is completely sorted, meaning that the method 'search' is guaranteed to only execute once due to the first if statement,
     * and the for loop is being executed n times in the worst case, so we can say that O(n) + O(logn) -> O(n).
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param m The matrix to search for val inside.
     * @param val The value to check if exists inside the matrix.
     * 
     * @return True if val exists inside m or false if not.
     *
     */

    public static boolean findValTest (int[][] m, int val) {
            for (int i=0; i<m.length; i++) {
                /* If val is inside the current row in the matrix, run binary search on the row to check if val exists inside it, 
                then return true or false accordingly. */
                if (val >= m[i][0] && val <= m[i][m[i].length - 1]) {
                    if (search(m[i], val)) return true;
                    return false;
                }
            }

            // Return false if val was not found in any row inside the matrix.
            return false;
    }

    /**
     *
     * This is private helper method that executes binary search on a given sorted integers array.
     * 
     * @param arr The sorted integers array to search inside.
     * @param num The integer to search for inside the array.
     * 
     * @return True if the integer was found inside the array or false if not.
     *
     */

    private static boolean search(int[] arr, int num) {
        int left=0, right=arr.length-1, mid;

        while (left <= right) {
            // Find the mid value of the current left to right range.
            mid = (left+right)/2;

            /* if the middle value is num, return true, as it was found, 
            otherwise check if the value should be to the right or to the left of mid, 
            then reassign right or left a new value accordingly. */
            if (arr[mid] == num) return true;
            if (arr[mid] < num) left=mid + 1;
            else right=mid-1;
        }

        // If num wasn't found after the binary search, return false.
        return false;
    }

    /**
     * 
     * Question 2
     * 
     * This method checks for the number of value-increasing sub-arrays that exist inside a given integers array.
     *
     * Time complexity:
     * O(n^2) — The for loop runs n times, and in the worst case, the while loop within it runs n-1 times. 
     * Therefore: O(n)*O(n-1) = O(n^2 - n) -> O(n^2).
     * 
     * Space complexity:
     * O(n) — The larger the array 'a', the more times the variables inside the for loop are being re-declared, in a linear way.
     * 
     * @param a The integers array to check for the number of value-increasing sub-arrays inside.
     * 
     * @return An integer that represents the number of value-increasing sub-arrays inside the integers array sent as a parameter.
     *
     */

    public static int strictlyIncreasing(int[] a) {
        /* Define a value-increasing sub-arrays counter and initialize to 0. */
        int counter = 0;

        /* For each cell in the array, check if the next cell's number is bigger. 
        If so — add 1 to counter and check the current cell's following cells as well while adding to the counter until the sequence stops. 
        If not, do nothing and move on to check the next cell in the array. */
        for (int i=0; i<a.length-1; i++) {
            if (a[i]<a[i+1]) {
                counter++;
                boolean isIncreasing = true;
                int j = i+1; // An index to keep track of the currently checked cell's following cells.

                while (isIncreasing && j<a.length-1) {
                    if (a[j]<a[j+1]) counter++;
                    else isIncreasing = false;
                    j++;
                }
            }
        }

        /* Finally, return the counter variable containing the number of 
        value-increasing sub-arrays inside the array sent as a parameter. */
        return counter;
    }

    /**
     * 
     * Question 3
     * 
     * This method checks for the longest sub-array that is also a flat sequence array inside a given integers array.
     * 
     * @param arr The array to check for the longest flat sequence sub-array inside.
     * 
     * @return An integer that represents the length of the longest flat sequence sub-array inside the given integers array.
     *
     */

    public static int longestFlatSequence(int[] arr) {
        return longestFlatSequence(arr, 0, 0);
    }

    /**
     *
     * This is a private overloading implementation of longestFlatSequence(int[] arr) that helps make its calculations in a recursive way, 
     * as more parameters are needed to be received other than just the parent array when calling the function again and again.
     * 
     * @param arr The array to check for the longest flat sequence sub-array inside.
     * @param index An integer that represents the index of the cell that is currently being checked inside the parent array.
     * @param max An integer that represents the length of the longest flat sequence sub-array that has been found so far.
     * 
     * @return An integer that represents the length of the longest flat sequence sub-array inside the given integers array.
     *
     */

    private static int longestFlatSequence(int[] arr, int index, int max) {
        /* If we haven't reached the end of the array yet,
        check if the current and next numbers in the array make up a flat sequence. */
        if (index < arr.length - 1) {
            if (arr[index] == arr[index+1] ||
                arr[index] == arr[index+1] + 1 ||
                arr[index] == arr[index+1] - 1) { 
                /* If the current and next numbers in the array make up a flat sequence, check its full length using a helper method, 
                then compare its length to the longest flat sequence sub-array that has been found so far. 
                Save the longest length in 'max' and move on to check the cell that comes after the sequence ends. */
                int length = lengthFlat(arr, index, arr[index], arr[index+1]);
                max = Math.max(max, length);
                index += length-1;

                return Math.max(max, longestFlatSequence(arr, index, max));
            }

            /* If the current and next numbers in the array don't make up a flat sequence, 
            move on to check the next cell in the parent array. */
            return Math.max(max, longestFlatSequence(arr, index+1, max));
        }

        /* Finally, return the longest length found. */
        return max;
    }

    /**
     *
     * This is private helper method for longestFlatSequence(int[] arr, int index, int max) 
     * that calculates the length of a given flat sequence array.
     * 
     * @param arr The parent array that contains the given flat sequence array.
     * @param index The index of the first integer in the flat sequence array.
     * @param val1 The first integer in the flat sequence array.
     * @param val2 The second integer in the flat sequence array (can be identical to first one).
     * 
     * @return An integer that represents the length of the flat sequence array.
     *
     */

    private static int lengthFlat(int[] arr, int index, int val1, int val2) {
        /* If the first two integers of the flat sequence array are identical, 
        constantly check if the next number in the array is a part of the sequence and is a neighbor number (+1/-1).
        If so, replace val2 with it so that we have the 2 numbers in the sequence saved inside val1 and val2. */
        if (val1 == val2)
            if (arr[index+1] == val1+1 || arr[index+1] == val1-1) 
                val2 = arr[index+1];

        /* If we haven't reached the end of the array yet, 
        check if the next number in the array is equal to one the integers of the sequence. 
        If so, add 1 to the sequence length and move on to check the next cell of the parent array.
        This will add 1 at least once, as we already know that val2 is a part of the flat sequence array within the parent array, 
        and is being tested first here. */
        if ((index < arr.length-1) && 
            (arr[index+1] == val1 || 
            arr[index+1] == val2))
                return lengthFlat(arr, index+1, val1, val2) + 1;


        /* The method will return 1 at least once, as the last number in a flat sequence array is still a part of the sequence. */
        return 1;
    }

    /**
     * 
     * Question 4
     * 
     * This method calculated the best score achievable within a certain route inside a given matrix.
     * 
     * @param mat The matrix to check for the route and its best achievable score inside.
     * 
     * @return An integer that represents the best achievable score.
     *
     */

    public static int findMaximum(int[][] mat) {
        // If the first cell of the route has a value of -1, return -1.
        if (mat[0][0] == -1) return -1;

        return findMaximum(mat, 0, 0);
    }

    /**
     *
     * This is a private overloading implementation of findMaximum(int[][] mat) that helps make its calculations in a recursive way, 
     * as more parameters are needed to be received other than just the matrix when calling the function again and again.
     * 
     * @param mat The matrix to check for the route and its best achievable score inside.
     * @param row An integer that represents the index of the row that is currently being routed inside the matrix.
     * @param col An integer that represents the index of the column that is currently being routed inside the matrix.
     * 
     * @return An integer that represents the best achievable score.
     *
     */

    private static int findMaximum(int[][] mat, int row, int col) {
        /* If the row index number is even, check if we've reached the last column. */
        if (row%2 == 0) {
            if (col < mat[row].length - 1) {
                /* If we haven't reached the last column, check if the cell on the right has a value of 0 or 1. */
                if (mat[row][col+1] == 1 || mat[row][col+1] == 0) {
                    /* Check if the current cell has a value of 1, 
                    if so — add 1 to the score and move on to the next cell in the route,
                    otherwise move on without adding score. */
                    if (mat[row][col] == 1) return findMaximum(mat, row, col+1) + 1;
                    return findMaximum(mat, row, col+1);
                }                

                /* If the cell on the right does not have a value of 0 or 1, no right turn is possible — break row using a helper method. */
                return goDown(mat, row, col);
            }
            
            /* If we have reached the last column, no right turn is possible — break row using a helper method. */
            return goDown(mat, row, col);
        }

        /* If the row index number is odd, check if we've reached the first column again. */
        else {
            if (col > 0) {
                /* If we haven't reached the first column again, check if the cell on the left has a value of 0 or 1. */
                if (mat[row][col-1] == 1 || mat[row][col-1] == 0) {
                    /* Check if the current cell has a value of 1, 
                    if so — add 1 to the score and move on to the next cell in the route,
                    otherwise move on without adding score. */
                    if (mat[row][col] == 1) return findMaximum(mat, row, col-1) + 1;
                    return findMaximum(mat, row, col-1);
                }

                /* If the cell on the left does not have a value of 0 or 1, no left turn is possible — break row using a helper method. */
                return goDown(mat, row, col);
            }
            
            /* If we have reached the first column again, no left turn is possible — break row using a helper method. */
            return goDown(mat, row, col);
        }
    }

    /**
     *
     * This is private helper method for findMaximum(int[][] mat, int row, int col)
     * that helps prevent repetitive code and executes a row break when needed while routing through the matrix.
     * 
     * @param mat The matrix to check for the route and its best achievable score inside.
     * @param row An integer that represents the index of the row that is currently being routed inside the matrix.
     * @param col An integer that represents the index of the column that is currently being routed inside the matrix.
     * 
     * @return An integer that represents the best achievable score found so far.
     *
     */

    private static int goDown(int[][] mat, int row, int col) {
        /* Check if we've reached the last row, if so — return 0 as no score can be added anymore. */
        if (row < mat.length - 1) {
            /* If we haven't reached the last row, check if the current cell has a value of 1.
            if so — add 1 to the score and break row to move on to the next cell in the route,
            otherwise move on without adding score. */
            if (mat[row][col] == 1) return findMaximum(mat, row+1, col) + 1;
            return findMaximum(mat, row+1, col);
        }

        return 0;
    }
}