import java.util.Iterator;
/**
 * Represents a block in blockchain.
 */
public class Block implements Comparable<Block>, Iterable<Transaction> {
    /**
     * Linked list of transactions contained in block.
     */
    private SinglyLinkedList<Transaction> trnsctn;
    /**
     * Root hash of block.
     */
    private String rh;
    /**
     * Number of transactions.
     */
    private int num;
    /**
     * An empty block with no transactions.
     */
    public Block() {
        trnsctn = new SinglyLinkedList<>();
        rh = null;
        num = 0;
    }

    /**
     * Adds transaction to the block and increments.
     * @param t transaction to be added.
     */

    public void addTransaction(Transaction t) {
        trnsctn.add(t);
        num++;
    }
    /**
     * Returns number of transactions.
     * @return the number of transactions.
     */

    public int numOfTransactions() {
        return num;
    }

    /**
     * Returns the root hash of the block.
     * @return the root hash.
     */
    public String getRootHash() {
        return rh;
    }
    /**
     * Sets root hash of block.
     * @param hashCode the new root hash.
     */
    public void setRootHash(String hashCode) {
        this.rh = hashCode;
    }

    /**
     * Compares this block with another block.
     *
     * @param other the block to be compared with.
     * @return a negative integer, zero, or a positive integer as this block.
     */

    @Override
    public int compareTo(Block other) {
        return Integer.compare(this.numOfTransactions(), other.numOfTransactions());
    }

    /**
     * Returns an iterator.
     * @return an Iterator over the transactions.
     */
    @Override
    public Iterator<Transaction> iterator() {
        return trnsctn.iterator();
    }
}
