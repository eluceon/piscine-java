public class User {
    private static Integer id = 0;
    private Integer userId;
    private String name;
    private Integer balance;

    User(String name, Integer balance) {
        this.balance = balance < 0 ? 0 : balance;
        this.name = name;
        this.userId = ++id;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
