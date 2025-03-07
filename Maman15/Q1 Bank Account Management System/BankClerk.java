import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class BankClerk extends Thread {
    private final TransactionPool transactionPool;
    private final Map<Integer, BankAccount> bankAccounts;

    public BankClerk(TransactionPool transactionPool, Map<Integer, BankAccount> bankAccounts) {
        this.transactionPool = transactionPool;
        this.bankAccounts = bankAccounts;
    }

    @Override
    public void run() {
        while (true) {
            TransactionPool.Transaction transaction = transactionPool.fetchTransaction();
            if (transaction == null) {
                System.out.println(Thread.currentThread().getName() + " found no more transactions and is stopping.");
                break;
            }

            BankAccount account = bankAccounts.get(transaction.getAccountNumber());
            if (account != null) {
                {
                    account.transaction(transaction.getAmount());
                }
                System.out.println(Thread.currentThread().getName() + " processed: " + transaction);
            } else {
                System.err.println("Account with number " + transaction.getAccountNumber() + " not found.");
            }
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(Thread.currentThread().getName() + " was interrupted.");
                break;
            }
        }
    }
}
