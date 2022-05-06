public class User {
	private String name;
	private Integer userId;
	private Integer balance;

	public User(String name, Integer balance) {
		this.name = name;
		if (balance > 0)
			this.balance = balance;
		else
			this.balance = 0;
		userId = UserIdsGenerator.getInstance().generateId();
	}
	
	public Integer getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return name + " (" + userId + ") " + balance;
	}
}
