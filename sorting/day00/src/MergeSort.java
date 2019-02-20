import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity:O(log(N))
     */

    @Override
    public int[] sort(int[] array) {
        // TODO

        if (array.length <= INSERTION_THRESHOLD) {
            InsertionSort insertsort = new InsertionSort();
            return insertsort.sort(array);
        } else {
            return splitsort(array);
        }
    }


    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */

    public int[] merge(int[] a, int[] b) {
        // TODO
        int[] merged = new int[a.length + b.length];
        int Ai = 0;
        int Bi = 0;
        int mergedi = 0;

        while(Ai < a.length && Bi < b.length){
            if (a[Ai] <= b[Bi]){
                merged[mergedi] = a[Ai];
                Ai++;
            }
            else{
                merged[mergedi] = b[Bi];
                Bi++;
            }
            mergedi++;
        }

        while(Ai < a.length){
            merged[mergedi] = a[Ai];
            Ai++;
            mergedi ++;
        }

        while(Bi < b.length){
            merged[mergedi] = b[Bi];
            Bi++;
            mergedi ++;

        }
        return merged;
    }

    /**
     * Given one array, splits the array in half, all sorted
     */

    public int[] splitsort(int[] array) {
        // TODO

        if (array.length == 1){
            return array;
        }

        int [] left;
        int [] right;

        if(array.length % 2 != 0){ //odd
            left = new int[(array.length/2)+1];
            right = new int[array.length - left.length];
            System.arraycopy(array, 0, left, 0, (array.length/2)+1);
            System.arraycopy(array, (array.length/2)+1, right, 0, array.length/2);

        }
        else{ //even
            left = new int[(array.length/2)];
            right = new int[array.length - left.length];
            System.arraycopy(array, 0, left, 0, array.length/2);
            System.arraycopy(array, array.length/2, right, 0, array.length/2);
        }

        left = splitsort(left);
        right =splitsort(right);

        return merge(left, right);
    }

}
