import java.util.Scanner;

public class MiniBank {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
      
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        int choice;
        do {
            
            System.out.println("\n=== MiniBank Menu ===");
            System.out.println("1. OPEN_ACCOUNT");
            System.out.println("2. DEPOSIT");
            System.out.println("3. WITHDRAW");
            System.out.println("4. TRANSFER");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");
            
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
        
        scanner.close();
    }
}

record BankInfo(String name, String branch) {
    @Override
    public String toString() {
        return "=== " + name + " - " + branch + " ===";
    }
}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}
