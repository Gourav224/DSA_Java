/*
 * Problem Statement: Shortest Common Supersequence
 * We are given two strings ‘S1’ and ‘S2’. We need to return their shortest common supersequence. 
 * A supersequence is defined as the string which contains both the strings S1 and S2 as subsequences.

 * Example:
 * Sl:"brute"   S2: "groot"
 * Shortest Common Supersequence: bgruoote
 * We need to return the length of the longest common subsequence and the string as well.
 */
public class ShortestCommonSupersequence {
    static String shortestSupersequence(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                else
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
            }
        }

        int i = n;
        int j = m;

        String ans = "";

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans += s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += s1.charAt(i - 1);
                i--;
            } else {
                ans += s2.charAt(j - 1);
                j--;
            }
        }

        // Adding Remaing Characters - Only one of the below two while loops will run

        while (i > 0) {
            ans += s1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            ans += s2.charAt(j - 1);
            j--;
        }

        String ans2 = new StringBuilder(ans).reverse().toString();

        return ans2;
    }

    public static void main(String args[]) {

        String s1 = "brute";
        String s2 = "groot";

        System.out.println("The Longest Common Supersequence is " + shortestSupersequence(s1, s2));
    }
}
