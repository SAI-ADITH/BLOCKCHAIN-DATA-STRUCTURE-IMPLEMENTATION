import java.util.LinkedList;
import java.util.Queue;

public class MerkleTree {

    private Node root;
    private int height = 1;
    private int innerNodeCount = 0;

    private class Node {
        String hash;
        Node left;
        Node right;

        Node(String hash) {
            this.hash = hash;
        }
    }

    public MerkleTree(Block block) {
        // Assuming Block class has a method getTransactions() that returns an array of transactions
        Transaction[] transactions = block.getTransactions();
        Queue<Node> queue = new LinkedList<>();

        for (Transaction t : transactions) {
            queue.add(new Node(t.getHash())); // Assuming Transaction class has a method getHash()
        }

        while (queue.size() > 1) {
            if (queue.size() % 2 != 0) {
                queue.add(queue.peek()); // Duplicate the last node if odd number of nodes
            }

            Queue<Node> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                Node left = queue.poll();
                Node right = queue.poll();
                Node parent = new Node(left.hash + right.hash); // Simplified hash function for illustration
                parent.left = left;
                parent.right = right;
                tempQueue.add(parent);
                innerNodeCount++;
            }
            queue = tempQueue;
            height++;
        }

        root = queue.poll();
    }

    public int height() {
        return height;
    }

    public int innerNodes() {
        return innerNodeCount;
    }

    public SinglyLinkedList<String> breadthFirstTraversal() {
        SinglyLinkedList<String> result = new SinglyLinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node.hash);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    public SinglyLinkedList<String> depthFirstTraversal(Order order) {
        SinglyLinkedList<String> result = new SinglyLinkedList<>();
        depthFirstHelper(root, result, order);
        return result;
    }

    private void depthFirstHelper(Node node, SinglyLinkedList<String> list, Order order) {
        if (node == null) return;

        if (order == Order.PRE_ORDER) {
            list.add(node.hash);
            depthFirstHelper(node.left, list, order);
            depthFirstHelper(node.right, list, order);
        } else if (order == Order.IN_ORDER) {
            depthFirstHelper(node.left, list, order);
            list.add(node.hash);
            depthFirstHelper(node.right, list, order);
        } else if (order == Order.POST_ORDER) {
            depthFirstHelper(node.left, list, order);
            depthFirstHelper(node.right, list, order);
            list.add(node.hash);
        }
    }

    public SinglyLinkedList<String> extractProof(Transaction t) {
        SinglyLinkedList<String> proof = new SinglyLinkedList<>();
        extractProofHelper(root, t.getHash(), proof);
        return proof;
    }

    private boolean extractProofHelper(Node node, String hash, SinglyLinkedList<String> proof) {
        if (node == null) return false;
        if (node.hash.equals(hash)) return true;

        if (extractProofHelper(node.left, hash, proof) || extractProofHelper(node.right, hash, proof)) {
            proof.add(node.hash);
            return true;
        }

        return false;
    }
}
