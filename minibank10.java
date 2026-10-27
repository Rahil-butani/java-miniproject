import java.util.Scanner;

import model.*;
import exception.*;

public class minibank10 {
    
    static class TransactionTask implements Runnable {
        private Account account;
        private long amount;
        private boolean isDeposit;
        
        public TransactionTask(Account account, long amount, boolean isDeposit) {
            this.account = account;
            this.amount = amount;
            this.isDeposit = isDeposit;
        }
        
        @Override
        public void run() {
            try {
                if (isDeposit) {
                    account.deposit(amount);
                    System.out.println(Thread.currentThread().getName() + " deposited " + amount);
                } else {
                    account.withdraw(amount);
                    System.out.println(Thread.currentThread().getName() + " withdrew " + amount);
                }
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " error: " + e.getMessage());
            }
        }
    }
    
    static class TransactionBuffer {
        private String[] buffer = new String[5];
        private int count = 0;
        
        public synchronized void produce(String transaction) throws InterruptedException {
            while (count == buffer.length) {
                wait();
            }
            buffer[count++] = transaction;
            System.out.println("Produced: " + transaction);
            notify();
        }
        
        public synchronized String consume() throws InterruptedException {
            while (count == 0) {
                wait();
            }
            String transaction = buffer[--count];
            System.out.println("Consumed: " + transaction);
            notify();
            return transaction;
        }
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        TransactionProcessor processor = new TransactionProcessor(4);
        Account acc = new SavingsAccount("Test User", 10000, 0);
        
        for (int i = 0; i < 10; i++) {
            processor.submit(new TransactionTask(acc, 100, true));
            processor.submit(new TransactionTask(acc, 50, false));
        }
        
        processor.stop();
        System.out.println("Final balance: " + acc.getBalance());
        
        System.out.println("\n=== Producer-Consumer ===");
        TransactionBuffer buffer = new TransactionBuffer();
        
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.produce("TXN-" + i);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
            }
        });
        
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.consume();
                    Thread.sleep(200);
                }
            } catch (Exception e) {
            }
        });
        
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        
        System.out.println("\n=== Deadlock Fix with Lock Ordering ===");
        Account a1 = new SavingsAccount("A", 5000, 0);
        Account a2 = new SavingsAccount("B", 5000, 0);
        
        Thread t1 = new Thread(() -> {
            try {
                transferSafe(a1, a2, 1000);
            } catch (Exception e) {
            }
        });
        
        Thread t2 = new Thread(() -> {
            try {
                transferSafe(a2, a1, 500);
            } catch (Exception e) {
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("A1: " + a1.getBalance() + ", A2: " + a2.getBalance());
        
        try {
            int choice = 0;
            do {
                System.out.println("\n=== MiniBank Menu ===");
                System.out.println("1. OPEN_ACCOUNT");
                System.out.println("2. DEPOSIT");
                System.out.println("3. WITHDRAW");
                System.out.println("4. TRANSFER");
                System.out.println("5. EXIT");
                System.out.print("Enter your choice: ");
                
                if (!scanner.hasNextInt()) {
                    break;
                }
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> System.out.println("Open Account — to be implemented in a later lab");
                    case 2 -> System.out.println("Deposit — to be implemented in a later lab");
                    case 3 -> System.out.println("Withdraw — to be implemented in a later lab");
                    case 4 -> System.out.println("Transfer — to be implemented in a later lab");
                    case 5 -> System.out.println("Thank you for using MiniBank. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } catch (Exception e) {
            System.out.println("Thank you for using MiniBank. Goodbye!");
        }
        
        scanner.close();
    }
    
    private static void transferSafe(Account from, Account to, long amount) throws Exception {
        Account first = from.getAccountNumber().compareTo(to.getAccountNumber()) < 0 ? from : to;
        Account second = first == from ? to : from;
        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transfered " + amount);
            }
        }
    }
}
