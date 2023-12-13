import java.util.Iterator;

/**
 * Represents singly linked list.
 * @param <T> the type of elements held in this list.
 */
public class SinglyLinkedList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * The head node.
     */
    private Node head;
    /**
     * The tail node.
     */
    private Node tail;
    /**
     * The size.
     */
    private int size;

    /**
     * Represents node in singly linked list.
     */

    private class Node {
        /**
         * Data stored in node.
         */
        T data;
        /**
         * Reference to next node.
         */
        Node next;

        /**
         * Constructs new node with the data.
         * @param data the data to be stored in node.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Constructs empty singly linked list.
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    /**
     * Adds new element to end of list.
     * @param value element to be added.
     */

    public void add(T value) {
        Node n;
        n = new Node(value);
        if (isEmpty()) {
            tail = n;
            head = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }
    /**
     * Inserts new element in list.
     * @param newValue the element to be inserted.
     */
    public void insert(T newValue) {
        Node newN = new Node(newValue);
        if (head == null) {
            head = newN;
            tail = newN;
        }
        else if (newValue.compareTo(head.data) >= 0) {
            newN.next = head;
            head = newN;
        }
        else {
            Node crnt;
            crnt = head;
            while ( crnt.next != null ) {
                if( crnt.next.data.compareTo(newValue) <= 0){
                    break;
                }
                crnt = crnt.next;
            }
            newN.next = crnt.next;
            crnt.next = newN;
            if (crnt == tail) {
                tail = newN;
            }
        }
        size++;
    }

    /**
     * Removes element at specified position.
     * @param index the index of the element.
     * @return the element that was removed.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node crnt;
        T remove;
        crnt = head;

        if (index == 0) {
            remove = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            for (int i = 0; i < index - 1; i++) {
                crnt = crnt.next;
            }
            remove = crnt.next.data;
            crnt.next = crnt.next.next;
            if (crnt.next == null) {
                tail = crnt;
            }
        }

        size--;
        return remove;
    }

    /**
     * Returns element at the specified position.
     * @param index the index of element to return.
     * @return the element at specified position.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node crnt;
        crnt = head;
        for (int i = 0; i < index; i++) {
            crnt = crnt.next;
        }
        return crnt.data;
    }

    /**
     * Returns number of elements.
     * @return the number of elements.
     */
    public int size() {
        return size;
    }
    /**
     * Returns true if list contains no elements.
     * @return true if this list is empty.
     */

    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns iterator over the elements in this list.
     * @return an Iterator over the elements.
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node crnt = head;

            @Override
            public boolean hasNext() {
                return crnt != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new RuntimeException();
                }
                T data = crnt.data;
                crnt = crnt.next;
                return data;
            }
        };
    }
}
