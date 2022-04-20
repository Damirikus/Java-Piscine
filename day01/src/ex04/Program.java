package ex04;

import java.security.cert.TrustAnchor;
import java.sql.DataTruncation;
import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User usr1 = new User("Bob", 500);
        User usr2 = new User("Jack", 200);
        User usr3 = new User("John", 2000);
        TransactionsService service = new TransactionsService();
        service.add(usr1);
        service.add(usr2);
        service.add(usr3);
        service.execute(usr1.getId(), usr2.getId(), 100);
        service.execute(usr2.getId(), usr1.getId(), 100);
        service.execute(usr1.getId(), usr2.getId(), 100);
        service.execute(usr1.getId(), usr2.getId(), 100);

        service.execute(usr2.getId(), usr3.getId(), 100);
        service.execute(usr2.getId(), usr3.getId(), 100);
        service.execute(usr2.getId(), usr3.getId(), 100);
        service.execute(usr2.getId(), usr3.getId(), 100);
        service.execute(usr1.getId(), usr3.getId(), 100);
        service.execute(usr1.getId(), usr3.getId(), 100);

        System.out.println(usr1);
        System.out.println(usr2);
        System.out.println(usr3);

        Transaction[] arr = service.getTransactionsArray(usr1.getId());
        for (Transaction t : arr){
            System.out.println(t);
        }

        System.out.println();

        arr = service.getTransactionsArray(usr3.getId());
        for (Transaction t : arr){
            System.out.println(t);
        }

        System.out.println();
        System.out.println();

        service.deleteTransaction(usr3.getId(), arr[1].getId());
        service.deleteTransaction(usr3.getId(), arr[2].getId());

        Transaction[] test = service.checkAllTransactions();
        for (Transaction t : test){
            System.out.println(t);
        }

    }
}
