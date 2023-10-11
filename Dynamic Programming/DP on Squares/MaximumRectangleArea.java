/*
 * Maximum Rectangle Area with all 1’s 
 * Problem Statement: Given a row X cols binary matrix filled with 0’s and 1’s, find the largest rectangle 
 * containing only 1’s and return its area.
 */

import java.util.Stack;

public class MaximumRectangleArea {
    public static int largestRectangleArea(int[] histo) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = histo.length;
        for (int i = 0; i <= n; i++) {
            while (!st.isEmpty() && (i == n || histo[st.peek()] >= histo[i])) {
                int height = histo[st.pop()];
                int width;
                if (st.isEmpty())
                    width = i;
                else
                    width = i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }
        return maxA;
    }

    public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
        int maxArea = 0;
        int[] height = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1)
                    height[j]++;
                else
                    height[j] = 0;
            }
            int area = largestRectangleArea(height);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] mat = {
                { 1, 0, 1, 0, 0 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }, 
                { 1, 0, 0, 1, 0 }
        };
        int n = 4, m = 5;
        int maxArea = maximalAreaOfSubMatrixOfAll1(mat, n, m);
        System.out.println("The maximum area is: " + maxArea);
    }
}
