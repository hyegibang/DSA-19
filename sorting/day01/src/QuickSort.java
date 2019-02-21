import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i + 1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlogn)
     * <p>
     * Space-complexity: O(logn)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int rand = random.nextInt(array.length);
            swap(array, i, rand);
        }
        quickSort(array, 0 , array.length - 1); //##

        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);

        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        // TODO
        int pivot = array[lo];
        while (lo < hi) {
            if (pivot > array[lo + 1]) {
                swap(array, lo, lo + 1);
                lo++;
            } else {
                swap(array, lo+1, hi);
                hi--;
            }
        }
        return lo;
    }
}
