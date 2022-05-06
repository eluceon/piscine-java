public class Program {
	public static void main(String[] args) {
        TransactionService ts = new TransactionService();
        User john = new User("John", 1000);
        User mike = new User("Mike", 5000);
        User eluceon = new User("Eluceon", 7777);
        User dima = new User("Dima", 99999);
        ts.addUser(john);
        ts.addUser(mike);
        ts.addUser(eluceon);
        ts.addUser(dima);

        System.out.println("User's blanes:");
        System.out.println("john: " + ts.getUserBalance(john.getUserId()) + ", mike: " + ts.getUserBalance(john)
            + ", eluceon: " + ts.getUserBalance(eluceon.getUserId()) + " dima: " + ts.getUserBalance(dima));

        try {
            ts.makeTransaction(john.getUserId(), mike.getUserId(), 100);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ts.makeTransaction(john.getUserId(), eluceon.getUserId(), -500);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ts.makeTransaction(eluceon.getUserId(), dima.getUserId(), 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            ts.makeTransaction(eluceon.getUserId(), dima.getUserId(), 5000000);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ts.makeTransaction(mike.getUserId(), dima.getUserId(), -10000000);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ts.makeTransaction(mike.getUserId(), dima.getUserId(), 100);
        } catch (Exception e) {
            System.out.println(e);
        }
		try {
            ts.makeTransaction(mike.getUserId(), dima.getUserId(), -500);
        } catch (Exception e) {
            System.out.println(e);
        }

		
		Transaction[] transactions = ts.getTransactions(mike);
		System.out.println("Mikes's transactions:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}
		
		transactions = ts.getTransactions(eluceon);	
		System.out.println("Eluceon's transactions:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}
		
		transactions = ts.getTransactions(john);
		System.out.println("John's transactions:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}
		
		transactions = ts.getTransactions(dima);	
		System.out.println("Dima's transactions:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}

		ts.removeTransaction(dima.getUserId(), transactions[1].getUuid());
		transactions = ts.getTransactions(dima);
		System.out.println("\nDima's transactions after deleting transaction:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}

		System.out.println("\nInvalid transactions:");
		Transaction invalidTransactions[] = ts.invalidTransactions();
		for (Transaction transaction : invalidTransactions) {
			System.out.println(transaction);
		}

		System.out.println("\nDima's balance: " + ts.getUserBalance(dima));
    }
}
