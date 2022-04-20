package ex04;

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

    public long getBalanceByIndex(int index){
        return usersList.getByIndex(index).getBalance();
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
            throw new IllegalTransactionException("not enough funds");
        }

    }


    public Transaction[] getTransactionsArray(int id){
        return usersList.getById(id).getTransactionsList().toArray();
    }

    public void deleteTransaction(int userId, UUID uuid){
        usersList.getById(userId).getTransactionsList().deleteById(uuid);
    }

    public Transaction[] checkAllTransactions(){
        if (usersList.size() > 0){
            TransactionsList list = new TransactionsLinkedList();
            User user = usersList.getByIndex(0);
            Transaction[] temp = user.getTransactionsList().toArray();
            for (Transaction transaction : temp) {
                list.add(transaction);
            }
            for (int i = 1; i < usersList.size(); i++){
                User u = usersList.getByIndex(i);
                Transaction[] inner = u.getTransactionsList().toArray();
                Transaction[] result = list.toArray();
                for (int k = 0; k < inner.length; k++){
                    boolean flag = false;
                    for (int j = 0; j < result.length; j++){
                        if (inner[k].getId().equals(result[j].getId())){
                            list.deleteById(result[j].getId());
                            flag = true;
                        }
                    }
                    if (!flag){
                        list.add(inner[k]);
                    }

                }
            }
            return list.toArray();
        }
        return null;
    }


    public static class IllegalTransactionException extends RuntimeException {
        public IllegalTransactionException(String message){
            super(message);
        }
    }

}
