public class EditDistance {

    public static int minEditDist(String a, String b) {
            int[][] DP = new int[a.length() + 1][b.length() + 1];

            for (int i = 0; i < a.length()+1; i++) {
                for (int j = 0; j < b.length()+1 ; j++) {
                    DP[i][j] = -1;
                }
            }
            for (int i = 0; i < a.length() + 1; i++) {
                for (int j = 0; j < b.length() + 1; j++) {
                    DP[i][0] = i;
                    DP[0][j] = j;
                }
            }


            for (int i = 1; i < a.length() + 1; i++) {
                for (int j = 1; j < b.length() + 1; j++) {
                    //delete
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        DP[i][j] = 0;
                    }

                    //insert
                    if (DP[i][j] == -1) {
                        int temp = Math.min(DP[i - 1][j - 1], DP[i][j - 1]);
                        DP[i][j] = Math.min(temp, DP[i - 1][j]) + 1;
                    }

                    //remove
                    if (DP[i][j] == 0) {
                        DP[i][j] = DP[i - 1][j - 1];
                    }
                }
            }

            return DP[a.length()][b.length()];
        }
    }

/*
* Bottom Up
* 1. Subproblem: number of iteration for string a to reach index i and string b up to index j for DP[i][j]
* 2. Guess: which function should be performed? or neither? --> replace, delete, insert
* 3. Recurrence: minimize the movement
* 4. Recurse: DP[i][j] = min(delete,inset,replace)
* 5. Solve: Solve original problem
* Runtime: o(n *m ) --> length of word n and word m
* */

/*
Top Down -> asuume that computed the subproblems
Bottom Up -> Fill a table in subproblem order and when solve look at previous result and move on
* */