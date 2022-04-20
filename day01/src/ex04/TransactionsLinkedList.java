package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Transaction first;
    private Transaction last;
    private int size;

    @Override
    public void add(Transaction transaction) {
        if (first == null && last == null){
            first = transaction;
            last = transaction;
            size++;
            return;
        }
        last.setNext(transaction);
        transaction.setPrev(last);
        last = transaction;
        size++;
    }

    @Override
    public void deleteById(UUID uuid) {
        Transaction current = first;
        while (current != null){
            if (current.getId() == uuid){
                if (current.getNext() == null && current.getPrev() == null){
                    first = null;
                    last = null;
                } else {
                    if (current.getPrev() != null){
                        current.getPrev().setNext(current.getNext());
                    } else {
                        first = current.getNext();
                        first.setPrev(null);
                    }
                    if (current.getNext() != null){
                        current.getNext().setPrev(current.getPrev());
                    } else {
                        last = current.getPrev();
                        last.setNext(null);
                    }
                }
                size--;
                return;
            }
            current = current.getNext();
        }
        throw new TransactionNotFoundException("Transaction not found!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[size];
        Transaction current = first;
        int i = 0;
        while (current != null){
            transactions[i] = current;
            current = current.getNext();
            i++;
        }
        return transactions;
    }


    public Transaction getFirst() {
        return first;
    }

    public void setFirst(Transaction first) {
        this.first = first;
    }

    public Transaction getLast() {
        return last;
    }

    public void setLast(Transaction last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message){
            super(message);
        }
    }
}
