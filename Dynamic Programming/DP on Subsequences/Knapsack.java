/*
 * Problem Statement: 0/1 Knapsack
 * A thief wants to rob a store. He is carrying a bag of capacity W. The store has ‘n’ items. 
 * Its weight is given by the ‘wt’ array and its value by the ‘val’ array. He can either include 
 * an item in its knapsack or exclude it but can’t partially have it as a fraction. We need to 
 * find the maximum value of items that the thief can steal.
 
 */

import java.util.Arrays;

public class Knapsack {
    // Helper function to solve the knapsack problem recursively
    static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {
        // Base case: If there are no items or the knapsack capacity is zero
        if (ind == 0) {
            if (wt[0] <= W) {
                // Include the item if its weight is within the capacity
                return val[0];
            } else {
                // Otherwise, exclude the item
                return 0;
            }
        }

        // If the result for this subproblem is already calculated, return it
        if (dp[ind][W] != -1) {
            return dp[ind][W];
        }

        // Calculate the maximum value when the current item is not taken
        int notTaken = 0 + knapsackUtil(wt, val, ind - 1, W, dp);

        // Calculate the maximum value when the current item is taken
        int taken = Integer.MIN_VALUE;
        if (wt[ind] <= W) {
            taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp);
        }

        // Store and return the result for the current state
        dp[ind][W] = Math.max(notTaken, taken);
        return dp[ind][W];
    }

    // Function to solve the 0/1 Knapsack problem using dynamic programming
    static int knapsack(int[] wt, int[] val, int n, int W) {
        // Create a 2D DP array to store the maximum value for each subproblem
        int dp[][] = new int[n][W + 1];

        // Initialize the DP array with -1 to indicate that subproblems are not solved
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        // Call the recursive knapsackUtil function to solve the problem
        return knapsackUtil(wt, val, n - 1, W, dp);
    }

    public static void main(String args[]) {
        int wt[] = { 1, 2, 4, 5 };
        int val[] = { 5, 4, 8, 6 };
        int W = 5;
        int n = wt.length;

        // Calculate and print the maximum value of items the thief can steal
        System.out.println("The Maximum value of items the thief can steal is " + knapsack(wt, val, n, W));
    }

    // Tabulation Approach
    // Function to solve the 0/1 Knapsack problem using dynamic programming
    static int knapsack1(int[] wt, int[] val, int n, int W) {
        // Create a 2D DP array to store the maximum value for each subproblem
        int dp[][] = new int[n][W + 1];

        // Base Condition
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {
                // Calculate the maximum value when the current item is not taken
                int notTaken = dp[ind - 1][cap];

                // Calculate the maximum value when the current item is taken
                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + dp[ind - 1][cap - wt[ind]];
                }

                // Store the maximum value for the current state
                dp[ind][cap] = Math.max(notTaken, taken);
            }
        }

        // The result is stored in the last row and last column of the DP array
        return dp[n - 1][W];
    }

}
