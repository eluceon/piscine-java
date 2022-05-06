import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private class Node {
        public Transaction transaction;
        public Node next;
        public Node prev;

        public Node(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
            this.prev = null;
        }

        public Node(Transaction transaction, Node next, Node prev) {
            this.transaction = transaction;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" + "transaction = " + transaction.toString() + '}';
        }
    }

    private int size;
    private Node head;
    private Node tail;


    public TransactionsLinkedList() {
        head = new Node(null);
        tail = new Node(null);

		head.next = tail;
		tail.prev = head;
        size = 0;
    }

    @Override
    public boolean add(Transaction transaction) {
        Node newNode = new Node(transaction, tail, tail.prev);
		tail.prev.next = newNode;
		tail.prev = newNode;
        ++size;
        return true;
    }

    @Override
    public Transaction remove(UUID uuid) {
        Node node = head.next;

        while (node != tail) {
            if (node.transaction.getUuid().equals(uuid)) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
                --size;
                return node.transaction;
            }
            node = node.next;
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        int i = 0;

        for (Node node = head.next; node != tail; node = node.next) {
            array[i] = node.transaction;
            ++i;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }
}
