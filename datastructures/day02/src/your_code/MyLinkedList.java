package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        // TODO
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public void add(Chicken c) {

        addLast(c);
    }

    public Chicken pop() {

        return removeLast();
    }

    public void addLast(Chicken c) {
        // TODO
        if (size != 0) {
            Node node1 = new Node(c, tail, null);
            tail.next = node1;
            tail = node1;
        }
        else {
            Node node1 = new Node(c, null,null);
            head = node1;
            tail = node1;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        // TODO
        if (size != 0) {
            Node node1 = new Node(c, null, head);
            head.prev = node1;
            head = node1;
        }
        else {
            Node node1 = new Node(c, null,null);
            head = node1;
            tail = node1;
        }
        size++;
    }

    public Chicken get(int index) {
        // TODO
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node current = head;
            int i = 0;
            while(i < index){
                current = current.next;
                i++ ;
            }
            return current.val;
        }
    }

    public Node getNode(int index) {
        // TODO
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node current = head;
            int i = 0;
            while(i < index){
                current = current.next;
                i++ ;
            }
            return current;
        }
    }

    public Chicken remove(int index) {
        // TODO

        if (index == 0 )
            return removeFirst();
         if(index == size-1)
            return removeLast();
         Node getnode = getNode(index);
         getnode.prev.next = getnode.next;
         getnode.next.prev = getnode.prev;
         size --;

        return getnode.val;
    }

    public Chicken removeFirst() {
        // TODO
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        Node removef = head;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = removef.next; // different from head = removef.next?
            head.prev = null;
        }
        size--;
        return removef.val;
    }

    public Chicken removeLast() {
        // TODO
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        Node removel = tail;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = removel.prev;
            tail.next = null;
        }
        size--;
        return removel.val;
    }
}
