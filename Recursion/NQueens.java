import java.util.ArrayList;

public class NQueens {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> ans=nQueen(4);
        for(ArrayList<Integer> t:ans){
            for(int j:t){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        // code here
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = 0;
        ArrayList < ArrayList < Integer >> res = new ArrayList < > ();
        int leftRow[] = new int[n];
        int upperDiagonal[] = new int[2 * n - 1];
        int lowerDiagonal[] = new int[2 * n - 1];
        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal,n);
        return res;
    }
    static void solve(int col,int[][] board,ArrayList < ArrayList < Integer >> res,int[] leftrow,int[] ld,int[] ud,int n ){
        if(col==n){
            res.add(construct(board,n));
            return ;
        }
        for(int i=0;i<n;i++){
            if(leftrow[i]==0 && ld[i+col]==0 && ud[n-1+col-i]==0){
                leftrow[i]=1;
                ld[i+col]=1;
                ud[n-1+col-i]=1;
                board[i][col]=1;
                solve(col+1, board, res, leftrow, ld, ud,n);
                leftrow[i]=0;
                ld[i+col]=0;
                ud[n-1+col-i]=0;
                board[i][col]=0;
                
            }
        }
        
    }
    static ArrayList<Integer> construct(int[][] board,int n){
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[j][i]==1){
                    ans.add(j+1);
                    break;
                }
            }
        }
        return ans;
    }
}
