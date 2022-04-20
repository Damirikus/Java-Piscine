package ex05;

public class User {
    private int id;
    private String name;
    private long balance;
    private TransactionsList transactionsList;

    public User(String name, long balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
        this.transactionsList = new TransactionsLinkedList();
    }

    public User() {
    }

    public void increase(long amount){
        setBalance(balance + amount);
    }

    public void withdraw(long amount){
        setBalance(balance - amount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        if (balance < 0)
            System.out.println("Error: negative amount - " + balance);
        else
            this.balance = balance;

    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(TransactionsList transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
