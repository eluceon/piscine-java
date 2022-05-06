import java.util.Objects;
import java.util.UUID;

public class User {
	private String name;
	private Integer userId;
	private Integer balance;
	private TransactionsList transactions;

	public User(String name, Integer balance) {
		this.name = name;
		transactions = new TransactionsLinkedList();
		this.balance = balance > 0 ? balance : 0;
		userId = UserIdsGenerator.getInstance().generateId();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public TransactionsList getTransactions() {
		return transactions;
	}

	public void setTransactions(TransactionsList transactions) {
		this.transactions = transactions;
	}

	public Integer getUserId() {
		return userId;
	}

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public void removeTransaction(Transaction transaction) {
		transactions.remove(transaction.getUuid());
	}

	public void removeTransaction(UUID uuid) {
		transactions.remove(uuid);
	}

	public void removeTransaction(String uuidString) {
		transactions.remove(uuidString);
	}

	public Transaction[] getArrayOfTransactions() {
        return transactions.toArray();
    }

	public void	increaseBalance(Integer sum) {
		balance += sum;
	}
	
	public void	decreaseBalance(Integer sum) {
		balance -= sum;
	}

	@Override
	public String toString() {
		return name + " (" + userId + ") " + balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return name.equals(user.name) && userId.equals(user.userId) && balance.equals(user.balance)
				&& Objects.equals(transactions, user.transactions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, userId, balance, transactions);
	}
}
