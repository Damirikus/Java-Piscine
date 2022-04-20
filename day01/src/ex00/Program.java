package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1,"Bob", 1000);
        User user2 = new User(2, "Jack", 700);
        User user3 = new User(3, "Bomj", -100);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);

        Transaction transaction1 = new Transaction(user1, user2, Transaction.Categories.DEBIT, 500);
        Transaction transaction2 = new Transaction(user1, user2, Transaction.Categories.DEBIT, -300);
        System.out.println(transaction1);
        System.out.println(transaction2);

        user1.setBalance(-400);
        user1.setBalance(2000);
        System.out.println(user1);
    }
}
