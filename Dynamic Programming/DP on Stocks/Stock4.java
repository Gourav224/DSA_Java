/*
 * We are given an array Arro of length n. It represents the price of a stock on 'n' days. The following guidelines need to be followed:
 * 1. We can buy and sell the stock any number Of times.
 * 2. In order to sell the stock, we need to first buy it on the same or any previous day.
 * 3. We can't buy a stock again after buying it once. In other words, we first buy a stock and then sell it. After
 *  selling we can buy and sell again. But we can't sell before buying and can't buy before selling any
 *  previously bought stock.
 * 4. We can do at-most K transactions.
 */

import java.util.Arrays;

public class Stock4 {

    // Recursive function to calculate the maximum profit
    public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
        // Base case
        if (ind == n || cap == 0) {
            return 0;
        }

        // If the result is already calculated, return it
        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }

        int profit = 0;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(prices, n, ind + 1, 0, cap, dp),
                    -prices[ind] + getAns(prices, n, ind + 1, 1, cap, dp));
        } else { // We can sell the stock
            profit = Math.max(0 + getAns(prices, n, ind + 1, 1, cap, dp),
                    prices[ind] + getAns(prices, n, ind + 1, 0, cap - 1, dp));
        }

        // Store the result in dp and return it
        dp[ind][buy][cap] = profit;
        return profit;
    }

    // Function to calculate the maximum profit
    public static int maximumProfit(int[] prices, int n, int k) {
        // Creating a 3D array dp of size [n][2][k+1]
        int[][][] dp = new int[n][2][k + 1];

        // Initialize dp with -1 to mark states as not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getAns(prices, n, 0, 0, k, dp);
    }

    public static void main(String[] args) {
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        int n = prices.length;
        int k = 2;

        System.out.println("The maximum profit that can be generated is " + maximumProfit(prices, n, k));
    }

    // Tabulation Approach
    // Function to calculate the maximum profit
    public static int maximumProfit1(int[] prices, int n, int k) {
        // Creating a 3D array dp of size [n+1][2][k+1] initialized to 0
        int[][][] dp = new int[n + 1][2][k + 1];

        // As dp array is initialized to 0, we have already covered the base case

        // Iterate through the array and fill in the dp array
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) { // We can buy the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                -prices[ind] + dp[ind + 1][1][cap]);
                    } else { // We can sell the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                prices[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }

        return dp[0][0][k];
    }
}
