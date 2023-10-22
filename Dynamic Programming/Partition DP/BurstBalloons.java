
/*
 * Burst Balloons | Partition DP | DP 51
 * Problem Statement: You are given n balloons, indexed from 0 to n – 1. Each balloon is painted with a 
 * number on it represented by an array. You are asked to burst all the balloons.

 * If you burst the ith balloon, you will get arr[i – 1] * arr[i] * arr[i + 1] coins. If i – 1 or i + 1 
 * goes out of the array’s bounds, then treat it as if there is a balloon with a 1 painted on it.
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * 
 */
import java.util.*;

public class BurstBalloons {
    // Function to recursively calculate the maximum coins
    static int burstBalloons(int i, int j, ArrayList<Integer> a, int[][] dp) {
        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) + burstBalloons(i, ind - 1, a, dp)
                    + burstBalloons(ind + 1, j, a, dp);
            maxi = Math.max(maxi, cost);
        }
        return dp[i][j] = maxi;
    }

    // Wrapper function to calculate the maximum coins
    static int burstBalloon(ArrayList<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return burstBalloons(1, n, a, dp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(3, 1, 5, 8));
        int ans = burstBalloon(a);
        System.out.println(ans);
    }

    // Tabulation Approach
    static int burstBalloon1(ArrayList<Integer> a) {
        int n = a.size();

        // Add 1 to the beginning and end of the array
        a.add(0, 1);
        a.add(1);

        // Create a 2D DP array
        int[][] dp = new int[n + 2][n + 2];

        // Iterate from the end to the beginning
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j)
                    continue;
                int maxi = Integer.MIN_VALUE;

                // Iterate through possible indices to split the array
                for (int ind = i; ind <= j; ind++) {
                    int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) +
                            dp[i][ind - 1] + dp[ind + 1][j];
                    maxi = Math.max(maxi, cost);
                }
                dp[i][j] = maxi;
            }
        }
        return dp[1][n];
    }
}
