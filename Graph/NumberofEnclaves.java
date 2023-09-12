/*
 * Number of Enclaves
 * Problem Statement: You are given an N x M binary matrix grid, where 0 represents a 
 * sea cell and 1 represents a land cell. A move consists of walking from one land cell
 *  to another adjacent (4-directionally) land cell or walking off the boundary of the grid. 
 * Find the number of land cells in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class NumberofEnclaves {
     int numberOfEnclaves(int[][] grid) {
        Queue<Pair2> q = new LinkedList<>();
        int n = grid.length; 
        int m = grid[0].length; 
        int vis[][] = new int[n][m];
        // traverse boundary elements
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                // first row, first col, last row, last col 
                if(i == 0 || j == 0 || i == n-1 || j == m-1) {
                    // if it is a land then store it in queue
                    if(grid[i][j] == 1) {
                        q.add(new Pair2(i, j)); 
                        vis[i][j] = 1; 
                    }
                }
            }
        }
        
        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, +1, +0, -1}; 
        
        while(!q.isEmpty()) {
            int row = q.peek().first; 
            int col = q.peek().second; 
            q.remove(); 
            
            // traverses all 4 directions
            for(int i = 0;i<4;i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i]; 
                // check for valid coordinates and for land cell
                if(nrow >=0 && nrow <n && ncol >=0 && ncol < m 
                && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair2(nrow, ncol));
                    vis[nrow][ncol] = 1; 
                }
            }
            
        }
        int cnt = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                // check for unvisited land cell
                if(grid[i][j] == 1 & vis[i][j] == 0) 
                    cnt++; 
            }
        }
        return cnt; 
        
    }
    public static void main(String[] args)
    {
        int grid[][] = {
        {0, 0, 0, 0},
        {1, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}};

        NumberofEnclaves ob = new NumberofEnclaves();
        int ans = ob.numberOfEnclaves(grid);
        System.out.println(ans);
    }

}
class Pair2 {
    int first;
    int second; 
    public Pair2(int first, int second) {
        this.first = first; 
        this.second = second; 
    }
}    
/*
 * Time Complexity: O(NxMx4) ~ O(N x M), For the worst case, assuming all the pieces as land,
 *  the BFS function will be called for (N x M) nodes and for every node, we are traversing for
 *  4 neighbors, so it will take O(N x M x 4) time. 

 * Space Complexity ~ O(N x M), O(N x M) for the visited array, and queue space takes up N x M locations at max. 
 */

