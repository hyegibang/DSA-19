import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    private static List<char[][]> nQueensHelper(char[][] board, int row, int[] columns, List<char[][]> curr){
        //base case -- if every row has valid queen placed in the board
        if(row==board.length)
            curr.add(copyOf(board));

        for(int i=0; i<board.length; i++){
            if(columns[i] == 0 && !checkDiagonal(board, row, i)){
                columns[i] = 1;
                board[row][i] = 'Q';
                nQueensHelper(board, row+1, columns, curr); //permutation -- check whether valid
                board[row][i] = '.';
                columns[i] = 0;
            }
        }
        return curr;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> result = new ArrayList<>();
        char[][] boards = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                boards[i][j] = '.';
            }
        }

        int[] columns = new int[n];
        result = nQueensHelper(boards,  0, columns, result);
        return result;
    }

}
// time O(n^2 * n!)
