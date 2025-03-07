# Maman15 Question 1: Bank Account Management System

## Overview
This is my solution for Question 1 of Maman15 assignment from the Advanced Programming in Java course (20554), implementing a multi-threaded bank account management system.

## Description
This program simulates a bank system where multiple bank clerks (threads) process transactions from a shared transaction pool. The system handles deposits and withdrawals for multiple bank accounts concurrently, demonstrating thread synchronization and management in Java.

## Implementation Details
- Multi-threaded application with 10 clerk threads processing transactions
- Thread-safe transaction pool shared between clerks
- Bank account operations (deposits and withdrawals)
- Random transaction generation
- Proper thread synchronization to prevent race conditions

## Key Components
- `Bank`: Main class that initializes accounts, transactions, and clerk threads
- `BankAccount`: Represents individual bank accounts with synchronized operations
- `TransactionPool`: Contains transactions to be processed by clerks
- `BankClerk`: Thread class that processes transactions from the pool

## How to Run
1. Clone this repository
2. Navigate to the project directory
3. Compile the Java files: `javac *.java`
4. Run the main class: `java Bank`

## Author
- Osher Akshikar
