/*
 * Problem Statement: Minimum Insertions/Deletions to Convert String A to String B

 * We are given two strings, str1 and str2. We are allowed the following operations:

 * Delete any number of characters from string str1.
 * Insert any number of characters in string str1.
 * We need to tell the minimum operations required to convert str1 to str2.
 */
public class ConvertString {
      // Function to find the length of the Longest Common Subsequence (LCS)
      static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store the LCS lengths
        int dp[][] = new int[n + 1][m + 1];

        // Initialize the first row and first column with 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        // Fill the dp array using a bottom-up approach
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                else
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        return dp[n][m];
    }

    // Function to find the minimum operations required to convert str1 to str2
    static int canYouMake(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // Find the length of the LCS between str1 and str2
        int k = lcs(str1, str2);

        // The minimum operations required is the sum of the lengths of str1 and str2 minus twice the length of LCS
        return (n - k) + (m - k);
    }

    public static void main(String args[]) {
        String str1 = "abcd";
        String str2 = "anc";
        System.out.println("The Minimum operations required to convert str1 to str2: "+ canYouMake(str1, str2));
    }
}
