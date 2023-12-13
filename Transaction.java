/**
 * Represents a single transaction in a blockchain system.
 */
public class Transaction implements Comparable<Transaction>
{
    /**
     * Sender's identifier.
     */

    private String sender;
    /**
     * Receiver's identifier.
     */
    private String receiver;
    /**
     * Amount of currency being transferred.
     */
    private int amount;
    /**
     * Transaction fee.
     */
    private int fee;

    /**
     * Constructs a new Transaction with specified information.
     * @param sender The identifier of sender.
     * @param receiver The identifier of receiver.
     * @param amount The amount of currency being transferred.
     * @param fee The fee associated with transaction.
     */
    public Transaction(String sender, String receiver, int amount, int fee)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.fee = fee;
    }
    /**
     * Returns transaction.
     * @return A string in the format "sender receiver amount fee".
     */
    public String toString()
    {
        return String.format("%s %s %d %d", sender, receiver, amount, fee);
    }
    /**
     * Returns the fee.
     * @return The fee with transaction.
     */
    public int getFee()
    {
        return fee;
    }
    /**
     * Compares this transaction with another transaction.
     * @param o The transaction being compared.
     * @return A negative integer, zero, or a positive integer.
     */

    @Override
    public int compareTo(Transaction o) {
        return Integer.compare(this.fee, o.fee);
    }
}
