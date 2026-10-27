import java.util.Scanner;

public class minibank2 {
    
    static class Customer {
        private String name;
        private String email;
        private String mobile;
        private final String customerId;
        private static long customerCounter = 100;
        
        private static String generateCustomerId() {
            customerCounter++;
            return "CUST" + customerCounter;
        }
        
        public Customer(String name, String email, String mobile) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.customerId = generateCustomerId();
        }
        
        public String getName() {
            return name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getMobile() {
            return mobile;
        }
        
        public String getCustomerId() {
            return customerId;
        }
    }
    
    static class Account {
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active;
        private static long accountCounter = 0;
        
        private static String generateAccountNumber() {
            accountCounter++;
            return String.format("AC%04d", accountCounter);
        }
        
        public Account(String ownerName, long openingBalance) {
            this.accountNumber = generateAccountNumber();
            this.ownerName = ownerName;
            this.balance = openingBalance;
            this.active = true;
        }
        
        public Account(String ownerName) {
            this(ownerName, 0);
        }
        
        public boolean deposit(long amount) {
            if (amount > 0) {
                balance += amount;
                return true;
            }
            return false;
        }
        
        public boolean withdraw(long amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
        
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public String getOwnerName() {
            return ownerName;
        }
        
        public long getBalance() {
            return balance;
        }
        
        public boolean isActive() {
            return active;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        Account[] accounts = new Account[3];
        accounts[0] = new Account("Rahul Sharma", 1000);
        accounts[1] = new Account("Priya Singh");
        accounts[2] = new Account("Amit Kumar", 5000);
        
        accounts[0].deposit(500);
        accounts[1].deposit(2000);
        accounts[2].withdraw(1000);
        accounts[0].withdraw(200);
        
        System.out.println("\n=== Account Balances ===");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account " + accounts[i].getAccountNumber() + " (" + 
                             accounts[i].getOwnerName() + "): Rs. " + accounts[i].getBalance());
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
