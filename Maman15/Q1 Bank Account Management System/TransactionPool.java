import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class TransactionPool {
    private final Queue<Transaction> transactions;

    public TransactionPool(Transaction[] transactionArray) {
        transactions = new LinkedList<>();
        Collections.addAll(transactions, transactionArray);
    }

    public synchronized Transaction fetchTransaction() {
        if (transactions.isEmpty()) {
            System.out.println("Transaction pool is empty.");
            return null;
        }
        Transaction transaction = transactions.poll();
        System.out.println("Processed transaction: " + transaction);
        return transaction;

    }

    public static class Transaction {
        private final int accountNumber;
        private final double amount;

        public Transaction(int accountNumber, double amount) {
            this.accountNumber = accountNumber;
            this.amount = amount;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public double getAmount() {
            return amount;
        }

        public String toString() {
            return "AccountNumber=" + accountNumber + ", amount=" + amount;
        }
    }
}
