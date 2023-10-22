/*
 * Problem statement:
 * Given a chain of matrices A1,…, An denoted by an array of size n+1, find out the minimum 
 * number of operations to multiply these n matrices.
 */
public class MatrixChainMultiplication {
    static int f(int i, int j, int[] arr, int[][] dp) {
        if (i == j) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int mini = f(i, k, arr, dp) + f(k + 1, j, arr, dp) + arr[i - 1] * arr[k] * arr[j];
            ans = Math.min(ans, mini);
        }
        dp[i][j] = ans;
        return ans;
    }

    static int matrixMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];
        return f(1, n - 1, arr, dp);
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50 };

        int n = arr.length;
        System.out.println("The minimum number of operations is " + matrixMultiplication(arr, n));
    }
    // Tabulation Approach
    int matrixMultiplication1(int[] arr, int N) {
        // Create a DP table to store the minimum number of operations
        int[][] dp=new int[N][N];
    
        // Initialize the diagonal elements of the DP table to 0
        for (int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }
    
        // Loop for the length of the chain
        for (int len = 2; len < N; len++) {
            for (int i = 1; i < N - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
    
                // Try different partition points to find the minimum
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
    
        // The result is stored in dp[1][N-1]
        return dp[1][N - 1];
    }
    
}
