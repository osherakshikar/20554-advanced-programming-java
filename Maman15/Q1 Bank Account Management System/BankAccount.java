import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int accountNumber;
    private double balance;
    private final Lock lock = new ReentrantLock();
    private final Condition sufficientFunds = lock.newCondition();

    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void transaction(double amount) {
        lock.lock();
        try {
            if (balance + amount < 0) {
                while (balance < -amount) {
                    System.out.println("Account " + accountNumber + ": Not enough funds. Waiting for deposit..");
                    sufficientFunds.await();
                }
                balance += amount;
                System.out.println("Account " + accountNumber + ": Withdrawal of " + (-amount) + " completed. New balance: " + balance);
            } else {
                balance += amount;
                System.out.println("Account " + accountNumber + ": Deposit of " + amount + " completed. New balance: " + balance);
                sufficientFunds.signalAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Account " + accountNumber + ": Transaction interrupted.");
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public String getAccountNumber() {
        return accountNumber + "";
    }
}
