public class SplitCoins {

    public static int splitCoinsRecursive(int[] coins, int sum1, int total, int index, int[][] DP){
        int sum2 = total-sum1;
        if(index==0){
            return Math.abs(sum1-sum2);
        }
        if(DP[index-1][sum1] != -1){
            return DP[index-1][sum1];
        }

        DP[index-1][sum1] = Math.min(splitCoinsRecursive(coins, sum1+coins[index-1], total, index-1, DP),
                splitCoinsRecursive(coins, sum1, total, index-1, DP));
        return DP[index-1][sum1];
    }

    public static int splitCoins(int[] coins) {
        int total = 0;
        for(int i=0; i<coins.length; i++){
            total=total+coins[i];
        }
        int[][] DP = new int[coins.length][total];

        for(int i=0; i< DP.length; i++){
            for(int j=0; j<DP[0].length; j++){
                DP[i][j] = -1;
            }
        }
        return splitCoinsRecursive(coins, 0, total, coins.length, DP);
    }
}
/* Top Down
* 1. Subproblem: DP[i][j] = minimize difference for only including up to index i in sum1
* 2. Guess: Should we include DP[i] to sum 1
* 3. Recurrence: minimize the difference between sum1 and sum2
* 4. Recurse: DP[i][j] = min(splitcoins(sum1 + coins[i], splitcoins(sum1))
* 5. Solve: Solve the original problem
* Runtime: O(n*s) --> s is the sum
* */

