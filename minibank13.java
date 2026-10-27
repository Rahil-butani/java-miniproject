import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import service.Repository;

public class minibank13 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        Repository<Account> accountRepo = new Repository<Account>();
        
        Account acc1 = new SavingsAccount("Rahul Sharma", 5000, 1000);
        Account acc2 = new CurrentAccount("Priya Singh", 2000, 5000);
        Account acc3 = new FixedDepositAccount("Amit Kumar", 10000);
        
        accountRepo.save(acc1);
        accountRepo.save(acc2);
        accountRepo.saveAll(acc3);
        
        System.out.println("\n=== Repository Operations ===");
        Account found = accountRepo.findById(acc1.getAccountNumber());
        System.out.println("Found by ID: " + found);
        
        ArrayList<Account> allAccounts = accountRepo.findAll();
        System.out.println("\nAll Accounts:");
        for (Account acc : allAccounts) {
            System.out.println(acc);
        }
        
        Double[] values = {10.5, 20.0, 30.5};
        System.out.println("\nAverage: " + Repository.average(values));
        
        Integer[] ints = {1, 2, 3, 4, 5};
        System.out.println("Average of ints: " + Repository.average(ints));
        
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
