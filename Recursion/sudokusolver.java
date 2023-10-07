import java.util.Arrays;

public class sudokusolver {

    public static void main(String[] args) {
        int[][] a = new int[9][9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(a[i], 0);
        }
        int[][] v = {
                { 0, 3, 0, 0, 0, 0, 2, 6, 0 },
                { 0, 0, 2, 6, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 3, 0, 0, 4, 0 },
                { 0, 0, 6, 0, 7, 0, 0, 0, 0 },
                { 0, 5, 0, 4, 0, 6, 0, 0, 1 },
                { 0, 0, 3, 9, 0, 2, 0, 1, 0 },
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 0, 0, 0, 0 }
        };
        sudokuSolver(v);
        for (int[] b : v) {
            for (int i : b) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    public static boolean sudokuSolver(int board[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isvalid(board, i, j, k)) {
                            board[i][j] = k;
                            if (sudokuSolver(board)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isvalid(int[][] board, int r, int c, int a) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == a || board[i][c] == a)
                return false;
            if (board[3 * (r / 3) + i / 3][3 * (c / 3) + i % 3] == a) {
                return false;
            }
        }
        return true;
    }
}
