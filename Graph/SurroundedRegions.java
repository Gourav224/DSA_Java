/*
 * Surrounded Regions | Replace O’s with X’s
 * Problem Statement: Given a matrix mat of size N x M where every element is 
 * either ‘O’ or ‘X’. Replace all ‘O’ with ‘X’ that is surrounded by ‘X’. 
 * An ‘O’ (or a set of ‘O’) is considered to be surrounded by ‘X’ if there are
 *  ‘X’ at locations just below, just above just left, and just right of it.
 */
public class SurroundedRegions {
    static void dfs(int row, int col,int vis[][], 
    char mat[][], int delrow[], int delcol[]) {
        vis[row][col] = 1; 
        int n = mat.length;
        int m = mat[0].length;
        
        // check for top, right, bottom, left 
        for(int i = 0;i<4;i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i]; 
            // check for valid coordinates and unvisited Os
            if(nrow >=0 && nrow <n && ncol >= 0 && ncol < m 
            && vis[nrow][ncol] == 0 && mat[nrow][ncol] == 'O') {
                dfs(nrow, ncol, vis, mat, delrow, delcol); 
            }
        }
    }

    static char[][] fill(int n, int m, char mat[][])
    {
        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, 1, 0, -1}; 
        int vis[][] = new int[n][m]; 
        // traverse first row and last row 
        for(int j = 0 ; j<m;j++) {
            // check for unvisited Os in the boundary rws
            // first row 
            if(vis[0][j] == 0 && mat[0][j] == 'O') {
                dfs(0, j, vis, mat, delrow, delcol); 
            }
            
            // last row 
            if(vis[n-1][j] == 0 && mat[n-1][j] == 'O') {
                dfs(n-1,j,vis,mat, delrow, delcol); 
            }
        }
        
        for(int i = 0;i<n;i++) {
            // check for unvisited Os in the boundary columns
            // first column 
            if(vis[i][0] == 0 && mat[i][0] == 'O') {
                dfs(i, 0, vis, mat, delrow, delcol); 
            }
            
            // last column
            if(vis[i][m-1] == 0 && mat[i][m-1] == 'O') {
                dfs(i, m-1, vis, mat, delrow, delcol); 
            }
        }
        
        // if unvisited O then convert to X
        for(int i = 0;i<n;i++) {
            for(int j= 0 ;j<m;j++) {
                if(vis[i][j] == 0 && mat[i][j] == 'O') 
                    mat[i][j] = 'X'; 
            }
        }
        
        return mat;
    }
     public static void main(String[] args)
    {
        char mat[][] = {
        {'X', 'X', 'X', 'X'}, 
        {'X', 'O', 'X', 'X'}, 
        {'X', 'O', 'O', 'X'}, 
        {'X', 'O', 'X', 'X'}, 
        {'X', 'X', 'O', 'O'}};

        // n = 5, m = 4
        
        char[][] ans = fill(5, 4, mat);
        for(int i = 0;i < 5;i++) {
            for(int j = 0;j < 4;j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
 * Time Complexity: O(N) + O(M) + O(NxMx4) ~ O(N x M), 
 * For the worst case, every element will be marked as ‘O’ in the matrix,
 *  and the DFS function will be called for (N x M) nodes and for every node,
 *  we are traversing for 4 neighbors, so it will take O(N x M x 4) time.
 *  Also, we are running loops for boundary elements so it will take O(N) + O(M).

 * Space Complexity ~ O(N x M), O(N x M) for the visited array, and auxiliary stack space takes up N x M locations at max. 
 
 */