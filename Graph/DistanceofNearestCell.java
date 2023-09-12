/*
 * Distance of Nearest Cell having 1
 * Problem Statement: Given a binary grid of N*M. Find the distance of the nearest 1 in the grid for each cell.

 * The distance is calculated as |i1  – i2| + |j1 – j2|, where i1, j1 are the row number and column number of
 * the current cell, and i2, j2 are the row number and column number of the nearest cell having value 1.
 */

import java.util.LinkedList;
import java.util.Queue;

public class DistanceofNearestCell {
    //Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid)
    {
        int n = grid.length; 
	    int m = grid[0].length; 
	    // visited and distance matrix
	    int vis[][] = new int[n][m]; 
	    int dist[][] = new int[n][m]; 
	    // <coordinates, steps>
	    Queue<Node1> q = new LinkedList<>();
	    // traverse the matrix
	    for(int i = 0;i<n;i++) {
	        for(int j = 0;j<m;j++) {
	        // start BFS if cell contains 1
	            if(grid[i][j] == 1) {
	                q.add(new Node1(i, j, 0)); 
	                vis[i][j] = 1; 
	            }
	            else {
	                // mark unvisted 
	                vis[i][j] = 0; 
	            }
	        }
	    }
	    
	    
	    
	    int delrow[] = {-1, 0, +1, 0}; 
	    int delcol[] = {0, +1, 0, -1}; 
	    
	    
	    // n x m x 4 
	    // traverse till queue becomes empty
	    while(!q.isEmpty()) {
	        int row = q.peek().first; 
	        int col = q.peek().second; 
	        int steps = q.peek().third; 
	        q.remove(); 
	        dist[row][col] = steps; 
	        // for all 4 neighbours
	        for(int i = 0;i<4;i++) {
	            int nrow = row + delrow[i]; 
	            int ncol = col + delcol[i]; 
	            // check for valid unvisited cell
	            if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
	            && vis[nrow][ncol] == 0)  {
	                    vis[nrow][ncol] = 1; 
    	            q.add(new Node1(nrow, ncol, steps+1));  
	            } 
	            }
	        }
	    
	    // return distance matrix
	    return dist; 
    }
     public static void main(String[] args)
    {
        int[][] grid = {
            {0,1,1,0},
            {1,1,0,0},
            {0,0,1,1}
        };

        DistanceofNearestCell obj = new DistanceofNearestCell();
        int[][] ans = obj.nearest(grid);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++){
                System.out.print(ans[i][j] + " "); 
            }
            System.out.println();
        }
    }
}
class Node1 {
    int first;
    int second;
    int third; 
    Node1(int _first, int _second, int _third) {
        this.first = _first; 
        this.second = _second; 
        this.third = _third; 
    }
}
/*
 * Time Complexity: O(NxM + NxMx4) ~ O(N x M)

   For the worst case, the BFS function will be called for (N x M) nodes, and for every node,
   we are traversing for 4 neighbors, so it will take O(N x M x 4) time.

 * Space Complexity: O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
  
   O(N x M) for the visited array, distance matrix, and queue space takes up N x M locations at max. 

 */