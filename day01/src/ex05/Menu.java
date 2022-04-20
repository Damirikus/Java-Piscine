package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static TransactionsService service;

    static {
        service = new TransactionsService();
    }

    private Menu(){
    }

    public static String addUser(){
        System.out.println("Enter a user name and a balance");
        while (true){
            Scanner scanner = new Scanner(System.in);
            try {
                String str = scanner.nextLine();
                if (str.isEmpty()){
                    continue;
                }
                String[] arr = str.split(" ");
                String name = arr[0];
                long balance = Integer.parseInt(arr[1]);
                User user = new User(name, balance);
                service.add(user);
                return "User with id = " + user.getId() + " is added";

            } catch (Exception e){
                System.out.println("Error: Incorrect arguments!");
                System.out.println("Enter a user name and a balance");
            }

        }
    }

    public static String getBalanceById(){
        System.out.println("Enter a user ID");
        while (true){
            Scanner scanner = new Scanner(System.in);
            try {
                if (service.getUsersList().size() == 0){
                    return "No user!";
                }
                int id = scanner.nextInt();
                String name = service.getUsersList().getById(id).getName();
                return name + " - " + service.getBalanceById(id);
            } catch (Exception e){
                System.out.println("Error: no such user, please enter a user ID");
            }
        }
    }

    public static String transfer(){
        System.out.println("Enter a sender ID,  a recipient ID, and a transfer amount");
        while (true){
            Scanner scanner = new Scanner(System.in);
            try {
                int sender = scanner.nextInt();
                int recipient = scanner.nextInt();
                int amount = scanner.nextInt();
                service.execute(recipient, sender, amount);
                return  "The transfer is completed!";
            } catch (TransactionsService.IllegalTransactionException | UsersArrayList.UserNotFoundException e){
                return e.getMessage();
            } catch (Exception e){
                System.out.println("Error: Incorrect arguments!");
                System.out.println("Enter a sender ID,  a recipient ID, and a transfer amount");
            }
        }
    }

    public static void getAllTransactions(){
        System.out.println("Enter a user ID");
        while (true){
            Scanner scanner = new Scanner(System.in);
            try{
                int id = scanner.nextInt();
                Transaction[] arr = service.getTransactionsArray(id);
                if (arr.length == 0){
                    System.out.println("No executed transactions!");
                    return;
                }
                for (Transaction t : arr){
                    if (t.getTransferCategory().equals(Transaction.Categories.DEBIT)){
                        System.out.println("From " + t.getSender().getName()
                                + "(id = " + t.getSender().getId()  + ") " +
                                t.getTransferAmount() + " with id = " + t.getId());
                    } else {
                        System.out.println("To " + t.getRecipient().getName()
                                + "(id = " + t.getRecipient().getId()  + ") " +
                                t.getTransferAmount() + " with id = " + t.getId());
                    }
                }
                return ;
            }catch (UsersArrayList.UserNotFoundException e){
                System.out.println(e.getMessage());
                return;
            }
            catch (Exception e){
                System.out.println("Error: Bad argument!");
            }
        }
    }

    public static void remove(){
        System.out.println("Enter a user ID and a transfer ID");
        while (true){
            Scanner scanner = new Scanner(System.in);
            try {
                String str = scanner.nextLine();
                String[] arrStr = str.split(" ");
                int userId = Integer.parseInt(arrStr[0]);

                UUID uuid = UUID.fromString(arrStr[1]);

                Transaction[] arr = service.getTransactionsArray(userId);
                for (Transaction t : arr){
                    if (t.getId().equals(uuid)){
                        service.deleteTransaction(userId, t.getId());
                        if (t.getTransferCategory().equals(Transaction.Categories.DEBIT)){
                            System.out.println("Transfer From " + t.getSender().getName() +
                                    "(id = " + t.getSender().getId()  + ") " +
                                    t.getTransferAmount() + " removed");
                        } else {
                            System.out.println("Transfer To " + t.getRecipient().getName() +
                                    "(id = " + t.getRecipient().getId()  + ") " +
                                    -t.getTransferAmount() + " removed");
                        }
                    }
                }
                return;

            } catch (TransactionsLinkedList.TransactionNotFoundException | UsersArrayList.UserNotFoundException e){
                System.out.println(e.getMessage());
                return;
            } catch (Exception e){
                System.out.println("Error: Bad arguments!");
                System.out.println("Enter a user ID and a transfer ID");
            }
        }
    }

    public static void checkTransfers(){
        try {
            System.out.println("Check");
            Transaction[] transactions = service.checkAllTransactions();
            if (transactions.length > 0){
                for (Transaction t : transactions){
                    if (t.getTransferAmount() > 0){
                        System.out.println(t.getRecipient().getName() +
                                "(id = " + t.getRecipient().getId() + ") has an unacknowledged transfer id = "
                                + t.getId() + " from " + t.getSender().getName() + "(id = "
                                + t.getSender().getId() + ") for " + t.getTransferAmount());
                    } else {
                        System.out.println(t.getSender().getName() +
                                "(id = " + t.getSender().getId() + ") has an unacknowledged transfer id = "
                                + t.getId() + " from " + t.getRecipient().getName() + "(id = "
                                + t.getRecipient().getId() + ") for " + -t.getTransferAmount());
                    }
                }
                return;
            }
            System.out.println("All transfers is valid!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void printMenuStandard(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balance");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. Finish execution");
    }

    public static void printMenuDev(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balance");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }
}
