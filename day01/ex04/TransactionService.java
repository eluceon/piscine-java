import java.util.UUID;

public class TransactionService {
	private UserList userList;
	private TransactionsList invalidTransactionsList;

	public TransactionService() {
		userList = new UserArrayList();
		invalidTransactionsList = new TransactionsLinkedList();
	}

	public void addUser(User user) {
		userList.add(user);
	}

	Integer getUserBalance(User user) {
		return userList.getById(user.getUserId()).getBalance();
	}

	Integer getUserBalance(Integer id) {
		return userList.getById(id).getBalance();
	}

	public void makeTransaction(Integer id1, Integer id2, Integer amount) {
		UUID uuid = UUID.randomUUID();

		if (amount == 0) {
			invalidTransactionsList.add(new Transaction(uuid, userList.getById(id1),
				userList.getById(id2), Transaction.TransferCategory.OUTCOME, amount));
			throw new IllegalTransactionException("Amount can't be 0");
		} else if (amount > 0) {
			Transaction tr1 = new Transaction(uuid, userList.getById(id1),
				userList.getById(id2), Transaction.TransferCategory.INCOME, amount);
            Transaction tr2 = new Transaction(uuid, userList.getById(id1),
				userList.getById(id2), Transaction.TransferCategory.OUTCOME, -amount);
			if (getUserBalance(id1) < amount) {
				invalidTransactionsList.add(tr2);
				throw new IllegalTransactionException("Illegal transaction");
			}
			userList.getById(id1).addTransaction(tr1);
			userList.getById(id2).increaseBalance(amount);
			userList.getById(id2).addTransaction(tr2);
			userList.getById(id1).decreaseBalance(amount);

           
		} else {
			Transaction tr1 = new Transaction(uuid, userList.getById(id2),
				userList.getById(id1), Transaction.TransferCategory.OUTCOME, amount);
			Transaction tr2 = new Transaction(uuid, userList.getById(id2),
				userList.getById(id1), Transaction.TransferCategory.INCOME, -amount);
			if (getUserBalance(id2) < -amount) {
				invalidTransactionsList.add(tr1);
				throw new IllegalTransactionException("Illegal transaction");
			}
			userList.getById(id1).addTransaction(tr1);
			userList.getById(id2).increaseBalance(amount);
			userList.getById(id2).addTransaction(tr2);
			userList.getById(id1).decreaseBalance(amount);

		}
	}

	public Transaction[] getTransactions(User user) {
		return user.getArrayOfTransactions();
	}

	public void removeTransaction(Integer userId, UUID transactionUuid) {
		userList.getById(userId).removeTransaction(transactionUuid);
	}

	public Transaction[] invalidTransactions() {
		return invalidTransactionsList.toArray();
    }



}