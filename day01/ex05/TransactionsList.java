import java.util.UUID;

public interface TransactionsList {
    boolean add(Transaction transaction);

	Transaction remove(String stringUuid);

    Transaction remove(UUID uuid);

    Transaction[] toArray();

    int size();

	boolean contains(UUID uuid);

	boolean contains(String stringUuid);

	Transaction get(UUID uuid);

	Transaction get(String stringUuid);
}
