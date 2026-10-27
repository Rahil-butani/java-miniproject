import java.util.Scanner;

import model.*;
import exception.*;

public class minibank9 {
    
    static class AccountWorker implements Runnable {
        private Account account;
        private int times;
        private long amount;
        
        public AccountWorker(Account account, int times, long amount) {
            this.account = account;
            this.times = times;
            this.amount = amount;
        }
        
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            for (int i = 0; i < times; i++) {
                try {
                    account.deposit(amount);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        Account sharedAccount = new SavingsAccount("Shared User", 1000, 0);
        Thread[] threads = new Thread[10];
        
        for (int i = 0; i < 10; i++) {
            AccountWorker worker = new AccountWorker(sharedAccount, 100, 10);
            threads[i] = new Thread(worker, "Worker-" + i);
            threads[i].start();
        }
        
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        
        System.out.println("\nFinal Balance: Rs. " + sharedAccount.getBalance());
        System.out.println("Expected: Rs. " + (1000 + 10 * 100 * 10));
        
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
}
