package ex05;

import java.util.Arrays;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService(){
        usersList = new UsersArrayList();
    }

    public void add (User user){
        usersList.add(user);
    }

    public long getBalanceById(int id){
        return usersList.getById(id).getBalance();
    }

    public void execute(int idRecipient, int idSender, long amount){
        if (getBalanceById(idSender) >= amount && amount > 0){
            Transaction transaction1 = new Transaction(
                    usersList.getById(idRecipient),
                    usersList.getById(idSender),
                    Transaction.Categories.DEBIT, amount);
            Transaction transaction2 = new Transaction(transaction1);
            transaction2.setTransferCategory(Transaction.Categories.CREDIT);
            transaction2.setTransferAmount(-transaction1.getTransferAmount());
            usersList.getById(idRecipient).getTransactionsList().add(transaction1);
            usersList.getById(idSender).getTransactionsList().add(transaction2);

            usersList.getById(idRecipient).increase(amount);
            usersList.getById(idSender).withdraw(amount);

        } else {
            throw new IllegalTransactionException("Error: Not enough money!");
        }

    }


    public Transaction[] getTransactionsArray(int id){
        return usersList.getById(id).getTransactionsList().toArray();
    }

    public void deleteTransaction(int userId, UUID uuid){
        usersList.getById(userId).getTransactionsList().deleteById(uuid);
    }

//    public Transaction[] checkAllTransactions(){
//        System.out.println("yyyyyyy = " + usersList.size());
//        if (usersList.size() > 1){
//            System.out.println("llll = " + usersList.size());
//            TransactionsList list = new TransactionsLinkedList();
//            User user = usersList.getByIndex(0);
//            System.out.println("7777777 = " + usersList.size());
//            Transaction[] temp = user.getTransactionsList().toArray();
//            System.out.println("iiiiii = " + usersList.size());
//            for (Transaction transaction : temp) {
//                list.add(transaction);
//            }
//            System.out.println("errrrrrrrrr = " + usersList.size());
//            for (int i = 1; i < usersList.size(); i++){
//                User u = usersList.getByIndex(i);
//
//                Transaction[] inner = u.getTransactionsList().toArray();
//                Transaction[] result = list.toArray();
//
//                System.out.println("AAAAAAAAA = " + usersList.size());
//
//                for (int k = 0; k < inner.length; k++){
//                    System.out.println("eeeeeeeeee = " + usersList.size());
//                    boolean flag = false;
//                    for (int j = 0; j < result.length; j++){
//                        if (inner[k].getId().equals(result[j].getId())){
//                            list.deleteById(result[j].getId());
//                            flag = true;
//                        }
//                    }
//                    if (!flag){
//                        list.add(inner[k]);
//                    }
//
//                }
//            }
//            return list.toArray();
//        }
//        return null;
//    }


    public Transaction[] checkAllTransactions() {
        TransactionsLinkedList list = new TransactionsLinkedList();
        Transaction head, tmp;
        if (usersList.size() == 0) {
            return null;
        }
        for (int i = 0; i < usersList.getByIndex(0).getTransactionsList().toArray().length; i++) {
            list.add(usersList.getByIndex(0).getTransactionsList().toArray()[i]);
        }
        for (int i = 1; i < usersList.size(); i++) {
            for (int j = 0; j < usersList.getByIndex(i).getTransactionsList().toArray().length; j++) {
                head = list.getFirst();
                tmp = usersList.getByIndex(i).getTransactionsList().toArray()[j];
                for (int k = 0; k < list.getSize(); k++) {
                    if (head.getId().equals(tmp.getId())) {
                        list.deleteById(head.getId());
                        tmp = null;
                        break;
                    }
                    head = head.getNext();
                }
                if (tmp != null) {
                    list.add(tmp);
                }
            }
        }
        return list.toArray();
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    public static class IllegalTransactionException extends RuntimeException {
        public IllegalTransactionException(String message){
            super(message);
        }
    }

}
