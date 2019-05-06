public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int x = map.length;
        int y = map[0].length;

        int DP[][] = new int[x][y];
        //destination
        if (map[x - 1][y - 1] > 0) {
            DP[x - 1][y - 1] = 1;
        }
        else {
            DP[x - 1][y - 1] = Math.abs(map[x - 1][y - 1]) + 1;
        }
        // last column
        for (int i = x - 2; i >= 0; i--) {
            DP[i][y - 1] = Math.max(DP[i + 1][y - 1] - map[i][y - 1], 1);
        }
        // last row
        for (int i = y - 2; i >= 0; i--) {
            DP[x - 1][i] = Math.max(DP[x - 1][i + 1] - map[x - 1][i], 1);
        }
        // rest of table
        for (int i = x - 2; i >= 0; i--) {
            for (int j = y - 2; j >= 0; j--) {
                int temp = Math.min(DP[i + 1][j], DP[i][j + 1]);
                DP[i][j] = Math.max(temp - map[i][j], 1);
            }
        }

        return DP[0][0];
    }
}

/*
* Bottom Up
* 1. Subproblem: Givin i and j as start, end point minimize the health
* 2. Guess: Direction to take
* 3. Recurssion Relation:  Max(Min(down, right, 1)
* 4. Recurse: DP[i][j] = Max(Min(down, right, 1)
* 5. Solve: solve the original problem
* Runtime: O(n^2)
f  * */
