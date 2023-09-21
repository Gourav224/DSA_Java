/*
 * Making a Large Island – DSU: 
 * Problem Statement: You are given an n x n binary grid. A grid is said to be binary if every value 
 * in the grid is either 1 or 0. You can change at most one cell in the grid from 0 to 1. You need to 
 * find the largest group of connected  1’s. Two cells are said to be connected if both are adjacent 
 * to each other and both have the same value.
 * 
 */

import java.util.HashSet;

public class MakingaLargeIsland {
    private static boolean isValid(int newr, int newc, int n) {
        return newr >= 0 && newr < n && newc >= 0 && newc < n;
    }

    public static int MaxConnection(int grid[][]) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        // step - 1
        for (int row = 0; row < n ; row++) {
            for (int col = 0; col < n ; col++) {
                if (grid[row][col] == 0) continue;
                int dr[] = { -1, 0, 1, 0};
                int dc[] = {0, -1, 0, 1};
                for (int ind = 0; ind < 4; ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];
                    if (isValid(newr, newc, n) && grid[newr][newc] == 1) {
                        int nodeNo = row * n + col;
                        int adjNodeNo = newr * n + newc;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }
        // step 2
        int mx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) continue;
                int dr[] = { -1, 0, 1, 0};
                int dc[] = {0, -1, 0, 1};
                HashSet<Integer> components = new HashSet<>();
                for (int ind = 0; ind < 4; ind++) {
                    int newr = row + dr[ind];
                    int newc = col + dc[ind];
                    if (isValid(newr, newc, n)) {
                        if (grid[newr][newc] == 1) {
                            components.add(ds.findUPar(newr * n + newc));
                        }
                    }
                }
                int sizeTotal = 0;
                for (Integer parents : components) {
                    sizeTotal += ds.size.get(parents);
                }
                mx = Math.max(mx, sizeTotal + 1);
            }
        }
        for (int cellNo = 0; cellNo < n * n; cellNo++) {
            mx = Math.max(mx, ds.size.get(ds.findUPar(cellNo)));
        }
        return mx;
    }

    public static void main (String[] args) {
        int[][] grid = {
            {1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1, 0}, {0, 0, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 0}
        };

        int ans = MaxConnection(grid);
        System.out.println("The largest group of connected 1s is of size: " + ans);
    }
}
/*
 * Time Complexity: O(N2)+O(N2) ~ O(N2) where N = total number of rows of the grid. Inside those nested loops, 
 * all the operations are taking apparently constant time. So, O(N2) for the nested loop only, is the time complexity.

 * Space Complexity: O(2*N2) where N = the total number of rows of the grid. This is for the two arrays i.e. 
 * parent array and size array of size N2 inside the Disjoint set.
 */
