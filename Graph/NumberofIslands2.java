/*
 * Number of Islands – II – Online Queries – DSU:
 * Problem Statement: You are given an n, m which means the row and column of the 2D matrix, 
 * and an array of size k denoting the number of operations. Matrix elements are 0 if there 
 * is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is 
 * no land in the matrix. The array has k operator(s) and each operator has two integers 
 * A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. 
 * Return how many islands are there in the matrix after each operation. You need to return an array of size k.

 * Note: An island means a group of 1s such that they share a common side.
 
 */

import java.util.ArrayList;
import java.util.List;

public class NumberofIslands2 {
    private static boolean isValid(int adjr, int adjc, int n, int m) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }
    public static List<Integer> numOfIslands(int n, int m, int[][] operators) {
        DisjointSet ds = new DisjointSet(n * m);
        int[][] vis = new int[n][m];
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        int len = operators.length;
        for (int i = 0; i < len ; i++) {
            int row = operators[i][0];
            int col = operators[i][1];
            if (vis[row][col] == 1) {
                ans.add(cnt);
                continue;
            }
            vis[row][col] = 1;
            cnt++;
            // row - 1, col
            // row , col + 1
            // row + 1, col
            // row, col - 1;
            int dr[] = { -1, 0, 1, 0};
            int dc[] = {0, 1, 0, -1};
            for (int ind = 0; ind < 4; ind++) {
                int adjr = row + dr[ind];
                int adjc = col + dc[ind];
                if (isValid(adjr, adjc, n, m)) {
                    if (vis[adjr][adjc] == 1) {
                        int nodeNo = row * m + col;
                        int adjNodeNo = adjr * m + adjc;
                        if (ds.findUPar(nodeNo) != ds.findUPar(adjNodeNo)) {
                            cnt--;
                            ds.unionBySize(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    public static void main (String[] args) {
        int n = 4, m = 5;
        int[][] operators = {{0, 0}, {0, 0}, {1, 1}, {1, 0}, {0, 1},
            {0, 3}, {1, 3}, {0, 4}, {3, 2}, {2, 2}, {1, 2}, {0, 2}
        };

        List<Integer> ans = numOfIslands(n, m, operators);

        int sz = ans.size();
        for (int i = 0; i < sz; i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println("");
    }
}
/*
 * Time Complexity: O(Q*4α) ~ O(Q) where Q = no. of queries. The term 4α is so small that it can be considered constant.

 * Space Complexity: O(Q) + O(N*M) + O(N*M), where Q = no. of queries, N = total no. of rows, M = total no. of columns. 
 * The last two terms are for the parent and the size array used inside the Disjoint set data structure. The first term 
 * is to store the answer.
 */