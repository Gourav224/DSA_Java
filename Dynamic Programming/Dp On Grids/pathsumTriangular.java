/*
 * Problem Description: 

 * We are given a Triangular matrix. We need to find the minimum path sum from the first row to the last row.

 * At every cell we can move in only two directions: either to the bottom cell (↓) or to the bottom-right cell(↘).
 
 */

import java.util.Arrays;

public class pathsumTriangular {
    // Function to find the minimum path sum in the triangle using dynamic
    // programming
    static int minimumPathSumUtil(int i, int j, int[][] triangle, int n, int[][] dp) {
        // Check if the result for the current position (i, j) is already calculated
        if (dp[i][j] != -1)
            return dp[i][j];

        // If we are at the bottom row, return the value in the triangle
        if (i == n - 1)
            return triangle[i][j];

        // Calculate the minimum path sum by recursively considering two possible paths:
        // down and diagonal
        int down = triangle[i][j] + minimumPathSumUtil(i + 1, j, triangle, n, dp);
        int diagonal = triangle[i][j] + minimumPathSumUtil(i + 1, j + 1, triangle, n, dp);

        // Store the result in the dp array and return the minimum of the two paths
        return dp[i][j] = Math.min(down, diagonal);
    }

    // Function to find the minimum path sum in the triangle
    static int minimumPathSum(int[][] triangle, int n) {
        // Create a 2D array to store computed results, initialize with -1
        int dp[][] = new int[n][n];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the utility function to find the minimum path sum starting from the top
        return minimumPathSumUtil(0, 0, triangle, n, dp);
    }

    public static void main(String args[]) {
        int triangle[][] = { { 1 },
                { 2, 3 },
                { 3, 6, 7 },
                { 8, 9, 6, 10 } };

        int n = triangle.length;

        // Call the minimumPathSum function and print the result
        System.out.println(minimumPathSum(triangle, n));
    }

    // Tabulation Approach
    // Function to find the minimum path sum in the triangle using dynamic
    // programming
    static int minimumPathSumTab(int[][] triangle, int n) {
        // Create a 2D array to store intermediate results
        int dp[][] = new int[n][n];

        // Initialize the bottom row of dp with the values from the bottom row of the
        // triangle
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        // Starting from the second to last row, calculate the minimum path sum for each
        // element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = triangle[i][j] + dp[i + 1][j];
                int diagonal = triangle[i][j] + dp[i + 1][j + 1];

                // Store the minimum of the two paths in dp
                dp[i][j] = Math.min(down, diagonal);
            }
        }

        // The result is stored at the top of dp array
        return dp[0][0];
    }
}
