public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+ max)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = get_max(A);
        int[] count = new int[k+1];
        for(int i =0; i < A.length; i++){
            count[A[i]] ++;
        }
        int j = 0;
        for (int i =0; i< count.length; i++){
            while(count[i] > 0){
                count[i]--;
                A[j] = i;
                j++;
            }
        }

    }

    static  int get_max(int[] A){
        int max = A[0];
        for(int i =0; i < A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        return max;
    }

}
