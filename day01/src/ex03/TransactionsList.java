package ex03;

import java.util.UUID;

public interface TransactionsList {
    public void add(Transaction transaction);
    public void deleteById(UUID uuid);
    public Transaction[] toArray();

}
