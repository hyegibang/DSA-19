
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N)^2
     * Average-case runtime: O(N)^2
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO

        for (int i = 1; i < array.length; i++ ){
            int p = i;
            while(p > 0 && array[p] < array[p-1] ){
                swap(array, (p-1), p);
                p --;

            }
        }
        return array;
    }
}
