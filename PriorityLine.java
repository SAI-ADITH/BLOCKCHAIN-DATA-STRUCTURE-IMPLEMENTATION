import java.util.Iterator;
/**
 * Represents a priority queue implemented using singly linked list.
 * @param <T> the type of elements held in this priority queue.
 */
public class PriorityLine<T extends Comparable<T>> implements Iterable<T> {

    /**
     * Singly linked list used to store elements.
     */

    private SinglyLinkedList<T> lst;
    /**
     * Constructs an empty priority queue.
     */
    public PriorityLine() {
        lst = new SinglyLinkedList<>();
    }
    /**
     * Inserts specified element.
     * @param element the element to add.
     */
    public void enqueue(T element) {

        lst.insert(element);
    }
    /**
     * Retrieves and removes the head of queue.
     * @return the head of this queue.
     * @throws RuntimeException if the priority queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The priority queue is empty.");
        }
        return lst.remove(0);
    }
    /**
     * Returns number of elements in priority queue.
     * @return number of elements in priority queue.
     */
    public int size() {
        return lst.size();
    }
    /**
     * Returns true if priority queue contains no elements.
     * @return true if priority queue is empty.
     */
    public boolean isEmpty() {
        return lst.isEmpty();
    }
    /**
     * Retrieves the head of this queue.
     * @return the head of this queue.
     * @throws RuntimeException if priority queue is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("The priority queue is empty.");
        }
        return lst.get(0);
    }
    /**
     * Returns iterator over the elements in priority queue.
     * @return an Iterator over elements in priority queue.
     */

    @Override
    public Iterator<T> iterator() {
        return lst.iterator();
    }
}
