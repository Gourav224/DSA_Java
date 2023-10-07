/*
 * Problem Description: 

 * We are given an “N*M” matrix of integers. We need to find a path from the top-left corner to the 
 * bottom-right corner of the matrix, such that there is a minimum cost past that we select.

 * At every cell, we can move in only two directions: right and bottom. The cost of a path is given 
 * as the sum of values of cells of the given matrix.
 
 */

import java.util.Arrays;

public class MinimumPathSum {
    // Helper function to calculate the minimum sum path in the matrix
    static int minSumPathUtil(int i, int j, int[][] matrix, int[][] dp) {
        // Base cases
        if (i == 0 && j == 0)
            return matrix[0][0]; // If we're at the top-left cell, return its value
        if (i < 0 || j < 0)
            return (int) Math.pow(10, 9); // If we're out of bounds, return a large value
        if (dp[i][j] != -1)
            return dp[i][j]; // If we've already calculated this cell, return the stored result

        // Calculate the sum of the current cell plus the minimum sum path from above
        // and from the left
        int up = matrix[i][j] + minSumPathUtil(i - 1, j, matrix, dp);
        int left = matrix[i][j] + minSumPathUtil(i, j - 1, matrix, dp);

        // Store the minimum of the two possible paths
        return dp[i][j] = Math.min(up, left);
    }

    // Main function to calculate the minimum sum path in the matrix
    static int minSumPath(int n, int m, int[][] matrix) {
        int dp[][] = new int[n][m];

        // Initialize the DP matrix with -1
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the helper function starting from the bottom-right cell
        return minSumPathUtil(n - 1, m - 1, matrix, dp);
    }

    public static void main(String args[]) {
        // Define the matrix
        int matrix[][] = {
                { 5, 9, 6 },
                { 11, 5, 2 }
        };

        int n = matrix.length;
        int m = matrix[0].length;

        // Calculate and print the minimum sum path in the matrix
        System.out.println(minSumPath(n, m, matrix));
    }

    // Tabulation Approach
    // Function to calculate the minimum sum path in the matrix
    static int minSumPathtab(int n, int m, int[][] matrix) {
        int dp[][] = new int[n][m];

        // Iterate through the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = matrix[i][j]; // If we're at the top-left cell, the minimum sum is its value
                else {
                    int up = matrix[i][j];
                    if (i > 0)
                        up += dp[i - 1][j]; // Add the value from above if it's not out of bounds
                    else
                        up += (int) Math.pow(10, 9); // Add a large value if out of bounds in the up direction

                    int left = matrix[i][j];
                    if (j > 0)
                        left += dp[i][j - 1]; // Add the value from the left if it's not out of bounds
                    else
                        left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

                    // Store the minimum of the two possible paths
                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        // The final result is stored in the bottom-right cell of the DP matrix
        return dp[n - 1][m - 1];
    }
}
