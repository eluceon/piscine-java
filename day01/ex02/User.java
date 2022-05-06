import java.util.Objects;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getUserId() {
		return userId;
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
		return name.equals(user.name) && userId.equals(user.userId) && balance.equals(user.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, userId, balance);
	}
}
