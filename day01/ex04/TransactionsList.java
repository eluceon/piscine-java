import java.util.UUID;

public interface TransactionsList {
    boolean add(Transaction transaction);

    Transaction remove(UUID uuid);

    Transaction[] toArray();

    int size();
}
