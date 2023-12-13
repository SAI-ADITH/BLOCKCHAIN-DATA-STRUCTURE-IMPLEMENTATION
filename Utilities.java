import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
/**
 * Provides utility methods for handling transactions and cryptographic hash functions.
 */
public final class Utilities
{
    /**
     * Reads transactions from text file and adds them to priority queue.
     * @param pgmFile The filename of text file.
     * @return A PriorityLine object containing the transactions.
     * @throws FileNotFoundException if file is not found.
     */
    public static PriorityLine<Transaction> loadTransactions(String pgmFile)
    {
        PriorityLine<Transaction> q;
        q = new PriorityLine<>();
        try (Scanner scnr = new Scanner(new File(pgmFile));)
        {
            while (scnr.hasNextLine())
            {
                String a;
                String[] b;
                a = scnr.nextLine();
                b = a.split(" ");
                if (b.length == 4)
                {
                    String sender = b[0];
                    String receiver = b[1];
                    int amount = Integer.parseInt(b[2]);
                    int fee = Integer.parseInt(b[3]);
                    Transaction transaction = new Transaction(sender, receiver, amount, fee);
                    q.enqueue(transaction);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found: " + pgmFile);
        }
        return q;
    }

    /**
     * Verifies if  transaction is contained in a certain block.
     * @param t The transaction to verify.
     * @param proof The list of hashes extracted.
     * @param blockRootHash The root hash code.
     * @return True if transaction is verified, else false.
     */
    public static boolean verifyTransaction(Transaction t, SinglyLinkedList<String> proof, String blockRootHash)
    {
        String transHash;
        transHash = cryptographicHashFunction(t.toString());
        for (String hash : proof)
        {
            transHash = cryptographicHashFunction(transHash, hash);
        }
        return transHash.equals(blockRootHash);
    }
    /**
     * SHA-256 cryptographic hash function for single input.
     * @param input The input string to hash.
     * @return The SHA-256 hash of the input.
     */
    public static String cryptographicHashFunction(String input)
    {
        StringBuilder hexString = null;

        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++)
            {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

        return hexString.toString();
    }

    /**
     * SHA-256 cryptographic hash function for a pair of inputs.
     * @param input1 The first input string to hash.
     * @param input2 The second input string to hash.
     * @return The SHA-256 hash of combined inputs.
     */
    public static String cryptographicHashFunction(String input1, String input2)
    {
        StringBuilder hexString = null;

        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash1 = digest.digest(input1.getBytes(StandardCharsets.UTF_8));
            byte[] encodedhash2 = digest.digest(input2.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(2 * encodedhash1.length);
            for (int i = 0; i < encodedhash1.length; i++)
            {
                String hex = Integer.toHexString(0xff & (encodedhash1[i] ^ encodedhash2[i]) );
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

        return hexString.toString();
    }

}
