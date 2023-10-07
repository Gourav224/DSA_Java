/*
 * Problem Description:

 * We are given an array ‘ARR’ of size ‘N’ and a number ‘Target’. Our task is to build an expression 
 * from the given array where we can place a ‘+’ or ‘-’ sign in front of an integer. We want to place 
 * a sign in front of every integer of the array and get our required target. We need to count the 
 * number of ways in which we can achieve our required target.
 */

import java.util.Arrays;

public class TargetSum {
    // Function to count partitions using dynamic programming
    static int countPartitionsUtil(int ind, int target, int[] arr, int[][] dp) {
        // Base case: If we have reached the first element
        if (ind == 0) {
            // Check if the target is 0 and the first element is also 0
            if (target == 0 && arr[0] == 0)
                return 2;
            // Check if the target is equal to the first element or 0
            if (target == 0 || target == arr[0])
                return 1;
            return 0;
        }

        // If the result for this subproblem has already been calculated, return it
        if (dp[ind][target] != -1)
            return dp[ind][target];

        // Calculate the number of ways without taking the current element
        int notTaken = countPartitionsUtil(ind - 1, target, arr, dp);

        // Initialize the number of ways taking the current element as 0
        int taken = 0;

        // If the current element is less than or equal to the target, calculate 'taken'
        if (arr[ind] <= target)
            taken = countPartitionsUtil(ind - 1, target - arr[ind], arr, dp);

        // Store the result in the dp array and return it
        return dp[ind][target] = (notTaken + taken);
    }

    // Function to find the number of ways to achieve the target sum
    static int targetSum(int n, int target, int[] arr) {
        int totSum = 0;

        // Calculate the total sum of elements in the array
        for (int i = 0; i < arr.length; i++) {
            totSum += arr[i];
        }

        // Checking for edge cases
        if (totSum - target < 0)
            return 0;
        if ((totSum - target) % 2 == 1)
            return 0;

        // Calculate the second sum based on the total sum and the target
        int s2 = (totSum - target) / 2;

        // Create a 2D array to store results of subproblems
        int dp[][] = new int[n][s2 + 1];

        // Initialize the dp array with -1 to indicate that subproblems are not solved
        // yet
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the countPartitionsUtil function to calculate the number of ways
        return countPartitionsUtil(n - 1, s2, arr, dp);
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 1 };
        int target = 3;

        int n = arr.length;

        // Call the targetSum function and print the result
        System.out.println("The number of ways found is " + targetSum(n, target, arr));
    }
    // Tabulation Approach
    static int mod = (int) (Math.pow(10, 9) + 7);

    // Function to find the number of ways to achieve the target sum
    static int findWays(int[] num, int tar) {
        int n = num.length;

        // Create a 2D array to store results of subproblems
        int[][] dp = new int[n][tar + 1];

        // Initialize the dp array for the first element of the array
        if (num[0] == 0)
            dp[0][0] = 2; // 2 cases - pick and not pick
        else
            dp[0][0] = 1; // 1 case - not pick

        if (num[0] != 0 && num[0] <= tar)
            dp[0][num[0]] = 1; // 1 case - pick

        // Fill the dp array using dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= tar; target++) {
                int notTaken = dp[ind - 1][target];

                int taken = 0;
                if (num[ind] <= target)
                    taken = dp[ind - 1][target - num[ind]];

                dp[ind][target] = (notTaken + taken) % mod;
            }
        }

        return dp[n - 1][tar];
    }
}
