package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private LinkedList<Integer> ll;

    public void enqueue(int item) {
        // TODO
        if (ll.isEmpty() || ll.getLast() < item)
            ll.addLast(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        // TODO
        return ll.remove(ll.size());
    }

}