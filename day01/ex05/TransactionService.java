import java.util.UUID;

public class TransactionService {
	private UserList userList;
	private TransactionsList invalidTransactionsList;
	private TransactionsList unpairedTrs = new TransactionsLinkedList();

	public TransactionService() {
		userList = new UserArrayList();
		invalidTransactionsList = new TransactionsLinkedList();
	}

	public void addUser(User user) {
		userList.add(user);
	}

	String getUserName(Integer id) {
		return userList.getById(id).getName();
	}

	Integer getUserBalance(User user) {
		return userList.getById(user.getUserId()).getBalance();
	}

	Integer getUserBalance(Integer id) {
		return userList.getById(id).getBalance();
	}

	public void makeTransaction(Integer senderID, Integer recipientID, Integer amount) {
		UUID uuid = UUID.randomUUID();
		
		if (amount == 0) {
			invalidTransactionsList.add(new Transaction(uuid, userList.getById(recipientID),
				userList.getById(senderID), Transaction.TransferCategory.OUTCOME, amount));
			throw new IllegalTransactionException("Amount can't be 0");
		} else if (amount > 0) {
			Transaction tr1 = new Transaction(uuid, userList.getById(recipientID),
				userList.getById(senderID), Transaction.TransferCategory.INCOME, amount);
            Transaction tr2 = new Transaction(uuid, userList.getById(recipientID),
				userList.getById(senderID), Transaction.TransferCategory.OUTCOME, -amount);
			if (getUserBalance(senderID) < amount) {
				invalidTransactionsList.add(tr2);
				throw new IllegalTransactionException("Illegal transaction");
			}
			userList.getById(recipientID).addTransaction(tr1);
			userList.getById(recipientID).increaseBalance(amount);
			userList.getById(senderID).addTransaction(tr2);
			userList.getById(senderID).decreaseBalance(amount);

           
		} else {
			Transaction tr1 = new Transaction(uuid, userList.getById(senderID),
				userList.getById(recipientID), Transaction.TransferCategory.OUTCOME, amount);
			Transaction tr2 = new Transaction(uuid, userList.getById(senderID),
				userList.getById(recipientID), Transaction.TransferCategory.INCOME, -amount);
			if (getUserBalance(senderID) < -amount) {
				invalidTransactionsList.add(tr1);
				throw new IllegalTransactionException("Illegal transaction");
			}
			userList.getById(recipientID).addTransaction(tr2);
			userList.getById(recipientID).increaseBalance(-amount);
			userList.getById(senderID).addTransaction(tr1);
			userList.getById(senderID).decreaseBalance(-amount);

		}
	}

	public Transaction[] getTransactions(User user) {
		return user.getArrayOfTransactions();
	}

	public Transaction[] getTransactions(Integer userId) {
		return userList.getById(userId).getArrayOfTransactions();
	}

	public void removeTransaction(Integer userId, UUID transactionUuid) {
		if (unpairedTrs.get(transactionUuid).getRecipient().getUserId() != userId
			&& (unpairedTrs.get(transactionUuid).getSender().getUserId() == userId))
		{
			throw new IllegalTransactionException("Illegal transaction"); 
		}
		if (unpairedTrs.contains(transactionUuid)) {
            unpairedTrs.remove(transactionUuid);
        } else if (unpairedTrs.get(transactionUuid).getRecipient().getUserId() == userId) {
            unpairedTrs.add(userList.getById(userId).getTransactions().get(transactionUuid));
        }

		userList.getById(userId).removeTransaction(transactionUuid);
	}

	public void removeTransaction(Integer userId, String stringUuid) {
		if (unpairedTrs.contains(stringUuid)) {
            unpairedTrs.remove(stringUuid);
        } else {
            unpairedTrs.add(userList.getById(userId).getTransactions().get(stringUuid));
        }

		userList.getById(userId).removeTransaction(stringUuid);
	}

	public Transaction[] invalidTransactions() {
		return invalidTransactionsList.toArray();
    }

	public Transaction[] getUnpairedTransactions() {
		return unpairedTrs.toArray();
	}
}