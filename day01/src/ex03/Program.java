package ex03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User usr1 = new User("Bob", 500);
        User usr2 = new User("Jack", 200);

        TransactionsLinkedList list = new TransactionsLinkedList();
        Transaction transaction = new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 100);

        list.add(transaction);

        System.out.println();
        System.out.println();

        Transaction[] trans = list.toArray();
        for (Transaction t : trans){
            System.out.println(t);
        }

        list.deleteById(transaction.getId());

        System.out.println();
        System.out.println();

        trans = list.toArray();
        for (Transaction t : trans){
            System.out.println(t);
        }


        Transaction transaction1 = new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 5000);
        Transaction transaction2 = new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 50);

        list.add(transaction2);
        list.add(new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 500));
        list.add(new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 1000));
        list.add(new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 2000));
        list.add(transaction);
        list.add(new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 3000));
        list.add(new Transaction(usr1, usr2, Transaction.Categories.DEBIT, 4000));
        list.add(transaction1);

        System.out.println(list.getSize());
        Transaction[] transactions = list.toArray();
        for (Transaction t : transactions){
            System.out.println(t);
        }


        System.out.println();
        System.out.println();

        list.deleteById(transaction2.getId());
        transactions = list.toArray();
        for (Transaction t : transactions){
            System.out.println(t);
        }

        System.out.println();
        System.out.println();

        list.deleteById(transaction.getId());
        transactions = list.toArray();
        for (Transaction t : transactions){
            System.out.println(t);
        }

        System.out.println();
        System.out.println();

        list.deleteById(transaction1.getId());
        transactions = list.toArray();
        for (Transaction t : transactions){
            System.out.println(t);
        }
        System.out.println(list.getSize());

        list.deleteById(UUID.randomUUID());

    }
}
