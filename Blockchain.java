import java.util.Iterator;
/**
 * Represents a blockchain.
 */
public class Blockchain implements Iterable<Block> {
    /**
     * A linked list of blocks the blockchain.
     */
    private SinglyLinkedList<Block> blk;
    /**
     * Constructs a blockchain from queue of transactions.
     * @param queue the queue of transactions to be added.
     * @param threshold the fee threshold for creating new block.
     */
    public Blockchain(PriorityLine<Transaction> queue, int threshold) {

        if (queue != null) {
            blk = new SinglyLinkedList<>();
            Block crnt = new Block();
            int f = 0;

            for (; !queue.isEmpty(); ) {
                Transaction trns = queue.peek();
                int p = f;
                p += trns.getFee();

                if (p > threshold) {
                    if (crnt.numOfTransactions() > 0) {
                        blk.add(crnt);
                    }
                    crnt = new Block();
                    f = 0;
                } else {
                    crnt.addTransaction(queue.dequeue());
                    f = p;
                }
            }
            if (crnt.numOfTransactions() > 0) {
                blk.add(crnt);
            }
        }
    }
    /**
     * Returns an iterator.
     * @return an Iterator over the blocks.
     */

    @Override
    public Iterator<Block> iterator() {
        return blk.iterator();
    }
}
