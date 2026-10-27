import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import model.*;
import util.StatePersister;
import util.TransactionLog;
import util.ReportGenerator;

public class minibank11 {
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        Account[] accounts = new Account[3];
        accounts[0] = new SavingsAccount("Rahul Sharma", 5000, 1000);
        accounts[1] = new CurrentAccount("Priya Singh", 2000, 5000);
        accounts[2] = new FixedDepositAccount("Amit Kumar", 10000);
        
        Path dataFile = Path.of("accounts.dat");
        StatePersister.save(accounts, dataFile);
        System.out.println("Accounts saved");
        
        Account[] loadedAccounts = StatePersister.load(dataFile);
        System.out.println("\n=== Loaded Accounts ===");
        for (Account acc : loadedAccounts) {
            System.out.println(acc);
        }
        
        Path logDir = Path.of("logs");
        if (!Files.exists(logDir)) {
            Files.createDirectories(logDir);
        }
        Path logFile = logDir.resolve("transactions.log");
        TransactionLog.append(logFile, "DEPOSIT AC0001 5000");
        TransactionLog.append(logFile, "WITHDRAW AC0002 1000");
        TransactionLog.append(logFile, "DEPOSIT AC0003 2000");
        
        ReportGenerator.generateReport(logDir, Path.of("eod_report.txt"));
        
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
