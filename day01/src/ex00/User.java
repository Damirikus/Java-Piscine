package ex00;

import java.math.BigDecimal;

public class User {
    private int id;
    private String name;
    private long balance;


    public User(int id, String name, long balance) {
        this.id = id; // надо нормально сделать айди
        this.name = name;
        setBalance(balance);
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
