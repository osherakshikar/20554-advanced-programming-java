# Bank Account Management System (Maman15 - Q1)

A multi-threaded banking system developed for the Advanced Programming in Java course (20554). Simulates multiple clerks processing transactions on shared bank accounts.

## Overview

- Concurrent transaction processing by 10 bank clerk threads  
- Shared, thread-safe transaction pool  
- Synchronized deposits and withdrawals on bank accounts  
- Random transaction generation  
- Proper use of synchronization to avoid race conditions

## Key Classes

- `Bank` – Initializes accounts, transactions, and starts clerk threads  
- `BankAccount` – Represents individual accounts with synchronized operations  
- `TransactionPool` – Thread-safe queue of pending transactions  
- `BankClerk` – Worker thread that processes transactions from the pool

## How to Run

1. Clone the repository  
2. Navigate to the project folder  
3. Compile all files:  
   ```bash
   javac *.java
4. Run the application
