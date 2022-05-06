import java.util.UUID;

public class Program {
	public static void main(String[] args) {
		User john = new User("John", 1000);
        User mike = new User("Mike", 5000);
        User eluceon = new User("Eluceon", 7777);
        User dima = new User("Dima", 99999);

        Transaction transaction1 = new Transaction(john, mike, Transaction.TransferCategory.INCOME , 300);
        Transaction transaction2 = new Transaction(mike, eluceon, Transaction.TransferCategory.OUTCOME , -3000);
        Transaction transaction3 = new Transaction(john, dima, Transaction.TransferCategory.INCOME , -10);
        Transaction transaction4 = new Transaction(dima, mike, Transaction.TransferCategory.OUTCOME , -300000);
		Transaction transaction5 = new Transaction(dima, eluceon, Transaction.TransferCategory.OUTCOME , 50);

		TransactionsList list = new TransactionsLinkedList();
		list.add(transaction1);
		list.add(transaction2);
		list.add(transaction3);
		list.add(transaction4);
		list.add(transaction5);
		Transaction[] transactions = list.toArray();
		System.out.println("All transactions");
		for(int i = 0; i < transactions.length; ++i) {
			System.out.println(transactions[i]);
		}
		System.out.println();

		try {
			list.remove(transaction2.getUuid());
			list.remove(transaction1.getUuid());
			list.remove(transaction5.getUuid());
			list.remove(UUID.randomUUID());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		transactions = list.toArray();
		System.out.println("\nTransactions after removing");
		for(int i = 0; i < transactions.length; ++i) {
			System.out.println(transactions[i]);
		}
		System.out.println();

		dima.addTransaction(transaction1);
		dima.addTransaction(transaction2);
		dima.addTransaction(transaction3);
		dima.addTransaction(transaction4);
		dima.addTransaction(transaction5);
		try {
			dima.removeTransaction(transaction2);
			dima.removeTransaction(transaction1.getUuid());
			dima.removeTransaction(transaction1.getUuid());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		transactions = dima.getArrayOfTransactions();
		System.out.println("\nDima's transactions after removing");
		for(int i = 0; i < transactions.length; ++i) {
			System.out.println(transactions[i]);
		}



	}
}
