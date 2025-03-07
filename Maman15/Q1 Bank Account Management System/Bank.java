import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Bank {
    public static void main(String[] args) {
        Map<Integer, BankAccount> bankAccounts = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            bankAccounts.put(i, new BankAccount(i, 0.0));
        }

        TransactionPool.Transaction[] transactions = new TransactionPool.Transaction[20];
        Random random = new Random();
        for (int i = 0; i < transactions.length; i++) {
            int accountNumber = random.nextInt(5);
            double amount = random.nextInt(2001) - 1000;
            transactions[i] = new TransactionPool.Transaction(accountNumber, amount);
        }
        TransactionPool transactionPool = new TransactionPool(transactions);

        BankClerk[] clerks = new BankClerk[10];
        for (int i = 0; i < clerks.length; i++) {
            clerks[i] = new BankClerk(transactionPool, bankAccounts);
        }

        for (int i = 0; i < clerks.length; i++) {
            clerks[i].start();
        }

        for (int i = 0; i < clerks.length; i++) {
            try {
                clerks[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
