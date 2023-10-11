/*
 * The longest increasing subsequence is described as a subsequence of an array where:
 *  All elements Of the subsequence are in increasing order.
 *  This subsequence itself is Of the longest length possible.
 
 * Examples
 
 * Array: [10, 9, 2, 5, 3, 7, 101, 18]
 * Longest Increasing Subsequence: [2, 3, 7, 101] or [2, 3, 7, 18]
 * The length of the longest increasing subsequence is 4.
 
 * We need to return the length of the longest increasing subsequence as the answer.
 */

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    // Function to find the length of the longest increasing subsequence
    static int getAns(int arr[], int n, int ind, int prev_index, int[][] dp) {
        // Base condition
        if (ind == n) {
            return 0;
        }

        if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index + 1];
        }

        int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns(arr, n, ind + 1, ind, dp);
        }

        dp[ind][prev_index + 1] = Math.max(notTake, take);

        return dp[ind][prev_index + 1];
    }

    // Function to find the length of the longest increasing subsequence
    static int longestIncreasingSubsequence(int arr[], int n) {
        int dp[][] = new int[n][n + 1];

        // Initialize dp array with -1 to mark states as not calculated yet
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        return getAns(arr, n, 0, -1, dp);
    }

    public static void main(String args[]) {
        int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int n = arr.length;
        System.out
                .println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
    }

    // Tabulation
    static int longestIncreasingSubsequence1(int arr[], int n) {

        int dp[][] = new int[n + 1][n + 1];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

                int notTake = 0 + dp[ind + 1][prev_index + 1];

                int take = 0;

                if (prev_index == -1 || arr[ind] > arr[prev_index]) {

                    take = 1 + dp[ind + 1][ind + 1];
                }

                dp[ind][prev_index + 1] = Math.max(notTake, take);

            }
        }

        return dp[0][0];
    }

}