public class LongestIncreasingSubsequence {

    private static int maxLIS;
    private static int LISRecursive(int[] A, int[] DP, int n){ // n is the length of array A
        if(n<1) {
            return 0;
        }else if(n==1){
            return 1;
        }else if(DP[n-1] > 0){
            return DP[n-1];
        }

        int localMaxLIS = 1; // length of LIS ending with arr[n-1]
        int result;

        /* Recursively get all LIS ending with arr[0], arr[1] ...
       arr[n-2]. If arr[i-1] is smaller than arr[n-1], and
       max ending with arr[n-1] needs to be updated, then
       update it */

        for (int i = 1; i < n; i++) {
            result = LISRecursive(A, DP, i);
            if (A[i-1] < A[n-1] && result + 1 > localMaxLIS) {
                localMaxLIS = result + 1;
            }
        }

        if (maxLIS < localMaxLIS)
            maxLIS = localMaxLIS;

        DP[n-1] = localMaxLIS;

        return localMaxLIS;

    }

    public static int LIS(int[] A) {
        maxLIS = 0;

        int[] DP = new int[A.length];
        LISRecursive(A, DP, A.length);

        return maxLIS;
    }
}

/*
1. subproblem: DP[i] = max LIS up to index i
2. guess: whether the next number is greater than current
3. recurrence relation: DP[i] = DP[i-1] + 1 if DP[i] is greater
4. recurse/memoize: store DP[i] into DP
5. solve original problem: return DP[i]

Runtime: O(N^2)
Space: O(N)
*/