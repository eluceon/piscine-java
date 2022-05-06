import java.util.Scanner;

public class Menu {
	private TransactionService transactionService = new TransactionService();
	Scanner sc = new Scanner(System.in);

	public Menu(boolean devmode) {
		makeMenu(devmode);
	}

	public void	makeMenu(boolean devmode) {
		String name;
		Integer intInput;
		User user;
		String menu = menu(devmode);

		while (true) {
			System.out.println("---------------------------------------------------------");
			System.out.print(menu);
			try {
				int menuNbr = sc.nextInt();
				switch (menuNbr) {
					case 1:
						System.out.print("Enter a user name and a balance\n-> ");
						try {
							name = sc.next();
							intInput = sc.nextInt();
							user = new User(name, intInput);
							transactionService.addUser(user);
							System.out.println("User with id = " + user.getUserId() + " is added");			
						} catch (Exception e) {
							sc.next();
							System.err.println("Incorrerect input");
						}
						break;
					case 2:
						System.out.print("Enter a user ID\n-> ");
						try {
							intInput = sc.nextInt();
						} catch (Exception e) {
							sc.next();
							System.err.println("Incorrerect input");
							continue;
						}
						try {
							System.out.println(transactionService.getUserName(intInput)
								+ " - " + transactionService.getUserBalance(intInput));
						} catch (Exception e) {
							System.err.println(e.toString());
						}
						break;
					case 3:
						System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
						Integer senderID;
						Integer recipientID;
						Integer amount;
						try {
							senderID = sc.nextInt();
							recipientID = sc.nextInt();
							amount = sc.nextInt();
						} catch (Exception e) {
							sc.next();
							System.err.println("Incorrerect input");
							continue;
						}
						try {
							transactionService.makeTransaction(senderID, recipientID, amount);
							System.out.println("The transfer is completed");
						} catch (Exception e) {
							System.err.println(e.toString());
						}
						break;
					case 4:
						try {
							System.out.println("Enter a user ID");
							Transaction tr[] = transactionService.getTransactions(sc.nextInt());
							for (int i = 0; i < tr.length; ++i) {
								printTransactoin(tr[i]);;
                            }
						} catch (Exception e) {
							System.err.println(e.toString());
						}
						break;
					case 5:
						if (devmode) {
							System.out.println("Enter a user ID and a transfer ID");
							Integer userID = sc.nextInt();
							String trID = sc.next();
							transactionService.removeTransaction(userID, trID);
							System.out.printf("Transfer %s of %s(id = %d) removed\n",
									trID, transactionService.getUserName(userID), userID);
						} else {
							System.exit(0);
						}
						break;
					case 6:
						if (devmode) {
							System.out.println("Check results:");
							for (Transaction tr : transactionService.getUnpairedTransactions()) {
								printUnacknowledgedTransfers(tr);
							}
						}
						break;
					case 7:
						if (devmode) {
							System.exit(0);
						}
					default:
						System.out.println("Invalid input");
				}
			} catch (Exception e) {
				sc.next();
				System.err.println("Incorrerect input");
			}
		}
	}
	
	private void printUnacknowledgedTransfers(Transaction tr) {
		if (tr.getCategory() == Transaction.TransferCategory.OUTCOME) {
			System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
				tr.getRecipient().getName(), tr.getRecipient().getUserId(), tr.getUuid().toString(),
				tr.getSender().getName(), tr.getSender().getUserId(), -tr.getAmount());	
		} else {
			System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s to %s(id = %d) for %d\n",
				tr.getSender().getName(), tr.getSender().getUserId(), tr.getUuid().toString(),
				tr.getRecipient().getName(), tr.getRecipient().getUserId(), -tr.getAmount());
		}

	}

	private void printTransactoin(Transaction tr) {
		if (tr.getCategory().equals(Transaction.TransferCategory.INCOME)) {
			System.out.printf("From %s(id = %d) %d with id = %s\n",
					tr.getSender().getName(), tr.getSender().getUserId(), tr.getAmount(), tr.getUuid().toString());
		} else {
			System.out.printf("To %s(id = %d) %d with id = %s\n",
					tr.getRecipient().getName(), tr.getRecipient().getUserId(), tr.getAmount(), tr.getUuid().toString());
		}
	}


	private String menu(boolean devmode) {
		String menu = 
			"1. Add a user\n"
			+ "2. View user balances\n"
			+ "3. Perform a transfer\n"
			+ "4. View all transactions for a specific user\n";
		if (devmode) {
			menu +=
			"5. DEV - remove a transfer by ID\n"
			+ "6. DEV - check transfer validity\n"
			+ "7. Finish execution\n"
			+ "-> ";
		} else {
			menu += "5. Finish execution\n-> ";
		}
		return menu;
	}
}
