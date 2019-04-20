import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        this.tiles = b;
        this.n = b.length;

    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // TODO: Your code here
        int sumDist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = tiles[i][j]; // number of the tile
//                System.out.println(num);
                if (num != 0) {
                    int rowExp = (num - 1) / n;
//                    System.out.print("row");
//                    System.out.println(rowExp);
                    int colExp = (num - 1) % n;
//                    System.out.print("col");
//                    System.out.println(colExp);
                    sumDist += Math.abs(i - rowExp) + Math.abs(j - colExp);
                }
            }
        }
        return sumDist;
    }


    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        return (manhattan() == 0);
    }
    /*
    * Inversion - A pair of tiles if the values on tiles are in reverse order of their appearance in goal state
    * Counts inversions: It is not possible to solve an instance of 8 puzzle if number of
    * inversions is odd in the input state
     */
//
//    public static int getInvCount(int[][] arr){
//        int invcount = 0;
//
//        int [] oneD = new int [(arr.length * arr.length)];
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                oneD[(i * arr.length) + j] = arr[i][j];
//            }
//        }
//
//        for(int i =0; i < arr.length - 1; i++){
//            for(int j = i ; j < arr.length; j++) {
//                // Value 0 is used for empty space
//                if (oneD[j] != 0 && oneD[i] != 0 && oneD[i] > oneD[j]) {
//                    invcount++;
//                }
//            }
//        }
//        return invcount;
//    }

    public static int getInvCount(int[] arr){
        int invcount = 0;

        for(int i =0; i < arr.length - 1; i++){
            for(int j = i ; j < arr.length; j++) {
                // Value 0 is used for empty space
                if (arr[j] != 0 && arr[i] != 0 && arr[i] > arr[j]) {
                    invcount++;
                }
            }
        }
        return invcount;
    }

    public int[] twoDoneD(int[][] arr){
        int [] oneD = new int [(arr.length * arr.length)];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                oneD[(i * arr.length) + j] = arr[i][j];
            }
        }
        return oneD;
    }


    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        int [] newTiles = twoDoneD(tiles);
        int numInv = getInvCount(newTiles);
        System.out.println(numInv);
        //Check whether it is odd even
        return (numInv % 2 ==0);
    }

    /*
     * Creates a deep copy of the input array and returns it
     */
    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    /*
     * Return all neighboring boards in the state tree
     */


    public Iterable<Board> neighbors() {
        // TODO: Your code here
        ArrayList<Board> neighborBoard = new ArrayList<Board>();
        int[][] temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    if (i + 1 < n) { // move right
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i + 1][j];
                        temp[i + 1][j] = 0;
                        neighborBoard.add(new Board(temp));
                    }
                    if (i - 1 >= 0) { //move left
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i - 1][j];
                        temp[i - 1][j] = 0;
                        neighborBoard.add(new Board(temp));
                    }
                    if (j + 1 < n) { //move dowm
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i][j + 1];
                        temp[i][j + 1] = 0;
                        neighborBoard.add(new Board(temp));
                    }
                    if (j - 1 >= 0) { //move up
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i][j - 1];
                        temp[i][j - 1] = 0;
                        neighborBoard.add(new Board(temp));
                    }
                }

            }
        }
        return neighborBoard;
    }


    /*
     * Visualize Board -- prints out the board
     */
    public static void visualize(Board b) {
        for (int x = 0; x < b.tiles.length; x++) {
            for (int y = 0; y < b.tiles.length; y++) {
                System.out.print(b.tiles[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");

        Iterable<Board> it = board.neighbors();
        for (Board b : it) {
            visualize(b);
        }
    }
}

