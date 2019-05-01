public class DiceRollSum {
    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        return diceRollRecursive(N, DP);
    }

    public static int diceRollRecursive ( int N, int[] DP){

        if (N < 0) {
            return 0;
        } else if (N == 0) {
            return 1;
        } else if (DP[N] > 0) {
            return DP[N];
        }

        DP[N] = diceRollRecursive(N - 1, DP) + diceRollRecursive(N - 2, DP) + diceRollRecursive(N - 3, DP) +
                diceRollRecursive(N - 4, DP) + diceRollRecursive(N - 5, DP) + diceRollRecursive(N - 6, DP);

        return DP[N];
    }

}
/*
1. subproblem: DP[i] = Number of dice sequence that sum up to N, 10
2. guess: Guess what coin is next
3. recurrence relation: DP[N] = DP[N-1]...+ DP[N-6], N being the desired sum
4. recurse/memoize: store DP[i] in the DP
5. solve original problem: return DP[i]

 Runtime: O(N*6) --> 6 sided dice
 Space: O(N)
*/