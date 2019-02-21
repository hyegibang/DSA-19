public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {

        return 2*i + 1;
    }

    private int rightChild(int i) {

        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int index = i;
        if (size > leftChild(i) && heap[leftChild(i)] > heap[index]){
            index = leftChild(i);

        }

        if (size > rightChild(i) && heap[rightChild(i)] > heap[index]){
            index = rightChild(i);
        }

        if(index != i){
            swap(heap,index,i);
            sink(index);
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(N)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        size = array.length;

        for (int i=size-1; i>0; i--) {
            swap(array,i,0);
            size--;
            sink(0);
        }

        return array;
    }
}
