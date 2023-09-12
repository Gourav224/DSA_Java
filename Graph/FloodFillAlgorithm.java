/*
 * 
 Flood Fill Algorithm – Graphs
 Problem Statement: An image is represented by a 2-D array of integers, each integer representing
 the pixel value of the image. Given a coordinate (sr, sc) representing the starting pixel 
 (row and column) of the flood fill, and a pixel value newColor, “flood fill” the image.

 To perform a “flood fill”, consider the starting pixel, plus any pixels connected 4-directionally
 to the starting pixel of the same colour as the starting pixel, plus any pixels connected 
 4-directionally to those pixels (also with the same colour as the starting pixel), and so on.
 Replace the colour of all of the aforementioned pixels with the newColor.
 */

public class FloodFillAlgorithm {
    private void dfs(int row, int col, 
    int[][] ans,
    int[][] image, 
    int newColor, int delRow[], int delCol[],
    int iniColor) {
       // color with new color
       ans[row][col] = newColor; 
       int n = image.length;
       int m = image[0].length; 
       // there are exactly 4 neighbours
       for(int i = 0;i<4;i++) {
           int nrow = row + delRow[i]; 
           int ncol = col + delCol[i]; 
           // check for valid coordinate 
           // then check for same initial color and unvisited pixel
           if(nrow>=0 && nrow<n && ncol>=0 && ncol < m && 
           image[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
               dfs(nrow, ncol, ans, image, newColor, delRow, delCol, iniColor); 
           }
       }
   }
   public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
   {
       // get initial color
       int iniColor = image[sr][sc]; 
       int[][] ans = image; 
       // delta row and delta column for neighbours
       int delRow[] = {-1, 0, +1, 0};
       int delCol[] = {0, +1, 0, -1}; 
       dfs(sr, sc, ans, image, newColor, delRow, delCol, iniColor); 
       return ans;  
   }
   public static void main(String[] args)
   {
       int[][] image =  {
           {1,1,1},
           {1,1,0},
           {1,0,1}
       };

       // sr = 1, sc = 1, newColor = 2       
       FloodFillAlgorithm obj = new FloodFillAlgorithm();
       int[][] ans = obj.floodFill(image, 1, 1, 2);
       for(int i = 0; i < ans.length; i++){
           for(int j = 0; j < ans[i].length; j++)
               System.out.print(ans[i][j] + " ");
           System.out.println();
       }
   }
}
/*
 *
Time Complexity: O(NxM + NxMx4) ~ O(N x M)
For the worst case, all of the pixels will have the same colour, so DFS function will be 
called for (N x M) nodes and for every node we are traversing for 4 neighbours, so it will
take O(N x M x 4) time.

Space Complexity: O(N x M) + O(N x M)
O(N x M) for copied input array and recursive stack space takes up N x M locations at max. 
 */
