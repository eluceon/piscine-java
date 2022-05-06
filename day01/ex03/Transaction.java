import java.util.UUID;

public class Transaction {
    enum TransferCategory {
        INCOME,
        OUTCOME
    }
    private UUID uuid;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private Integer amount;

    public Transaction(User recipient, User sender, TransferCategory category, Integer amount) {
        this.uuid = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        this.amount = amount;
        setAmount(amount);
    }

    public void setAmount(Integer amount) {
        if ((amount < 0 && category == TransferCategory.INCOME)
                || (amount > 0 && category == TransferCategory.OUTCOME)
                || (category == TransferCategory.INCOME && amount > sender.getBalance())
                || (category == TransferCategory.OUTCOME && -amount > sender.getBalance()))
        {
            amount = 0;
        }
        this.amount = amount;
        amount = amount < 0 ? -amount : amount;
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return sender.getName() + " -> " + recipient.getName()
                + (amount > 0 ? ", +" : ", ")
                + amount + ", " + category + ", " + uuid;
    }
}
