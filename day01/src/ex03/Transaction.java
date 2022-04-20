package ex03;

import java.util.UUID;

public class Transaction {

    public enum Categories{
        DEBIT,
        CREDIT
    }
    private UUID id;
    private User recipient;
    private User sender;
    private Categories transferCategory;
    private long transferAmount;
    private Transaction next;
    private Transaction prev;

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    public Transaction getPrev() {
        return prev;
    }

    public void setPrev(Transaction prev) {
        this.prev = prev;
    }

    public Transaction(User recipient, User sender, Categories transferCategory, long transferAmount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Categories getTransferCategory() {
        return transferCategory;
    }

    public void setTransferCategory(Categories transferCategory) {
        this.transferCategory = transferCategory;
    }

    public long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(long transferAmount) {
        switch (transferCategory){
            case DEBIT:
                if (transferAmount < 0){
                    System.out.println("Error: negative credit - " + transferAmount);
                } else {
                    this.transferAmount = transferAmount;
                }
                break;
            case CREDIT:
                if (transferAmount > 0){
                    System.out.println("Error: positive credit - " + transferAmount);
                } else {
                    this.transferAmount = transferAmount;
                }
        }

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", recipient=" + recipient.getName() +
                ", sender=" + sender.getName() +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
