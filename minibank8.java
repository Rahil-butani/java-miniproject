import java.util.Scanner;

import model.*;
import exception.*;

public class minibank8 {
    
    static class TransactionLogger implements AutoCloseable {
        public TransactionLogger() {
            System.out.println("Transaction logger opened");
        }
        
        @Override
        public void close() {
            System.out.println("Transaction logger closed");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        try {
            Account acc1 = new SavingsAccount("Rahul Sharma", 5000, 1000);
            Account acc2 = new CurrentAccount("Priya Singh", 2000, 5000);
            
            try (TransactionLogger logger = new TransactionLogger()) {
                acc1.deposit(1000);
                System.out.println("Deposited successfully");
            }
            
            try {
                acc1.withdraw(-500);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            try {
                acc2.withdraw(10000);
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage() + ", Shortfall: " + e.getShortfall());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            try {
                acc1.transfer(acc2, 2000);
                System.out.println("Transfer completed");
            } catch (BankException e) {
                System.out.println("Transfer failed: " + e.getMessage());
            } finally {
                System.out.println("Finally block executed");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
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
