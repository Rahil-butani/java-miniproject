import java.util.Scanner;
import static java.lang.Math.abs;

import model.*;
import util.Validator;
import util.StatementFormatter;
import service.CommandParser;
import service.Command;

public class minibank6 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        Account[] accounts = new Account[3];
        accounts[0] = new SavingsAccount("Rahul Sharma", 5000, 1000);
        accounts[1] = new CurrentAccount("Priya Singh", 2000, 5000);
        accounts[2] = new FixedDepositAccount("Amit Kumar", 10000);
        
        System.out.println("\n=== Using Interfaces ===");
        for (Account acc : accounts) {
            acc.deposit(500);
            System.out.println(acc.getOwnerName() + " balance: Rs. " + acc.getBalance() + ", Yearly Interest: Rs. " + acc.yearlyInterest());
        }
        
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return amount > 0 && amount <= account.getBalance();
            }
        };
        
        WithdrawRule rule2 = (account, amount) -> amount > 0 && amount <= account.getBalance() * 2;
        
        System.out.println("\nRule1 allows withdraw 1000 from savings: " + rule1.allow(accounts[0], 1000));
        System.out.println("Rule2 allows withdraw 10000 from savings: " + rule2.allow(accounts[0], 10000));
        System.out.println("Abs of -5: " + abs(-5));
        
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
