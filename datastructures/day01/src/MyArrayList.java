public class MyArrayList {
    private Cow[] elems; // Array behind Arraylist that hold value
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        // TODO
        elems = new Cow[10]; // creating array of size 10
        this.size = 0; // ArrayList
    }

    //constructor
    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        this.size = 0;
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        grow();
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        // TODO
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }
        return elems[index];
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        // TODO
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }
        Cow selected = elems[index];
        for (int i = 0; i < size; i++) {
            if (i > index) {
                elems[i - 1] = elems[i];
            }
        }
        size--;
        grow();
        return selected;

    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        // TODO
        grow();
        size++;
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }

        Cow temp = elems[index];
        elems[index] = c;
        for (int i = index + 1; i < size; i++) {
                Cow temp2 = temp;
                temp = elems[i];
                elems[i] = temp2;
        }
    }

    //TODO: Runtime: O(N)
    public void grow() {
        int newsize;
        if (size == elems.length / 4)   {
            newsize = elems.length / 2;
            Cow[] newcow = new Cow[newsize];
            System.arraycopy(elems, 0, newcow, 0, newsize);
            elems = newcow;

        } else if (size >= elems.length) {
            newsize = elems.length * 2;
            Cow[] newcow = new Cow[newsize];
            System.out.println(newcow.length);
            System.arraycopy(elems, 0, newcow, 0, size);
            elems = newcow;
        }

    }
}

