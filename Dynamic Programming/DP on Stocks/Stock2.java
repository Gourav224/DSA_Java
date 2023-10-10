/*
 * Problem Statement: Buy and Sell Stock – II 
 * We are given an array Arr[] of length n. It represents the price of a stock on ‘n’ days. The following guidelines need to be followed:

 * We can buy and sell the stock any number of times.
 * In order to sell the stock, we need to first buy it on the same or any previous day.
 * We can’t buy a stock again after buying it once. In other words, we first buy a stock and then sell it. After selling we can buy and sell again. But we can’t sell before buying and can’t buy before selling any previously bought stock.
 */
public class Stock2 {
    // Recursive function to calculate the maximum profit
    static long getMaximumProfitUtil(long[] Arr, int ind, int buy, int n, long[][] dp) {
        // Base case
        if (ind == n)
            return 0;

        // If the result is already computed, return it
        if (dp[ind][buy] != 0)
            return dp[ind][buy];

        long profit = 0;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getMaximumProfitUtil(Arr, ind + 1, 0, n, dp),
                    -Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 1, n, dp));
        }

        if (buy == 1) { // We can sell the stock
            profit = Math.max(0 + getMaximumProfitUtil(Arr, ind + 1, 1, n, dp),
                    Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 0, n, dp));
        }

        // Store the result in the dp table and return it
        dp[ind][buy] = profit;
        return profit;
    }

    // Function to calculate the maximum profit
    static long getMaximumProfit(long[] Arr, int n) {
        // Create a 2D vector for memoization (dp)
        long[][] dp = new long[n][n];

        // Base case: If n is 0, return 0 profit
        if (n == 0)
            return 0;

        // Calculate the maximum profit using the recursive function
        long ans = getMaximumProfitUtil(Arr, 0, 0, n, dp);
        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        long[] Arr = { 7, 1, 5, 3, 6, 4 };

        // Calculate and print the maximum profit
        System.out.println("The maximum profit that can be generated is " + getMaximumProfit(Arr, n));
    }

    // Tabulation Approach
    // Function to calculate the maximum profit
    static long getMaximumProfit1(long[] Arr, int n) {
        // Create a 2D array for dynamic programming with dimensions [n+1][2]
        long[][] dp = new long[n + 1][2];

        // Base condition: If we have no stocks to buy or sell, profit is 0
        dp[n][0] = dp[n][1] = 0;

        long profit = 0;

        // Iterate through the array in reverse to calculate the maximum profit
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 1][0]);
                }

                dp[ind][buy] = profit;
            }
        }
        return dp[0][0]; // The maximum profit is stored at dp[0][0]
    }
}
