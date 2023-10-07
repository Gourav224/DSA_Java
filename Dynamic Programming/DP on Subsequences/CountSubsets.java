/*
 * Problem Statement: Count Subsets with Sum K
 * We are given an array ‘ARR’ with N positive integers and an integer K. We need to find 
 * the number of subsets whose sum is equal to K.
 */

import java.util.Arrays;

public class CountSubsets {
    // Helper function to find the number of ways to achieve a target sum
    static int findWaysUtil(int ind, int target, int[] arr, int[][] dp) {
        if (target == 0)
            return 1;

        if (ind == 0)
            return arr[0] == target ? 1 : 0;

        if (dp[ind][target] != -1)
            return dp[ind][target];

        // Calculate the number of ways when the current element is not taken
        int notTaken = findWaysUtil(ind - 1, target, arr, dp);

        // Calculate the number of ways when the current element is taken
        int taken = 0;
        if (arr[ind] <= target)
            taken = findWaysUtil(ind - 1, target - arr[ind], arr, dp);

        // Store and return the result for the current state
        return dp[ind][target] = notTaken + taken;
    }

    // Main function to find the number of ways to form subsets with a target sum
    static int findWays(int[] num, int k) {
        int n = num.length;
        int dp[][] = new int[n][k + 1];

        for (int row[] : dp)
            Arrays.fill(row, -1);

        return findWaysUtil(n - 1, k, num, dp);
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 2, 3 };
        int k = 3;

        // Calculate and print the number of subsets that sum up to k
        System.out.println("The number of subsets found are " + findWays(arr, k));
    }

    // Tabulation Approach
    // Function to find the number of subsets with a given target sum
    static int findWays1(int[] num, int k) {
        int n = num.length;

        // Create a 2D DP array to store the number of ways to achieve each target sum
        int[][] dp = new int[n][k + 1];

        // Initialize the first row of the DP array
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // Initialize the first column of the DP array
        if (num[0] <= k) {
            dp[0][num[0]] = 1;
        }

        // Fill in the DP array using bottom-up dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                // Calculate the number of ways when the current element is not taken
                int notTaken = dp[ind - 1][target];

                // Calculate the number of ways when the current element is taken
                int taken = 0;
                if (num[ind] <= target) {
                    taken = dp[ind - 1][target - num[ind]];
                }

                // Update the DP array for the current element and target sum
                dp[ind][target] = notTaken + taken;
            }
        }

        // The result is stored in the last cell of the DP array
        return dp[n - 1][k];
    }
}
