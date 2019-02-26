
public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // Returns a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        quickSort(keys, locks, 0, locks.length - 1);
        result[0] = locks;
        result[1] = keys;

        System.out.println();
        return result;
    }

    private static void quickSort(char[] a, char[] b, int left, int right){
        if(left < right){

            int index = partition(a, left, right, b[right]);
            partition(b, left, right, a[index]);

            quickSort(a, b, left, index-1);
            quickSort(a, b, index+1, right);
        }
    }

    private static int partition(char[] array, int left1, int right1, char pivot){
        int i = left1;
        for(int j=left1; j<right1; j++){
            if(array[j] < pivot){
                swap(array, i, j);
                i++;
            }
            else if(array[j] == pivot){
                swap(array, j, right1);
                j--;
            }
        }
        swap(array, i, right1);
        return i;
    }
}