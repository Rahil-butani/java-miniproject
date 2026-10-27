import java.util.Scanner;

public class minibank3 implements Cloneable {
    
    static class Address {
        private String line;
        private String city;
        private String pincode;
        
        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }
        
        public String getLine() {
            return line;
        }
        
        public String getCity() {
            return city;
        }
        
        public String getPincode() {
            return pincode;
        }
    }
    
    static class Customer implements Cloneable {
        private String name;
        private String email;
        private String mobile;
        private final String customerId;
        private static long customerCounter = 100;
        private Address address;
        
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
        
        public Customer(String name, String email, String mobile, Address address) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.customerId = generateCustomerId();
            this.address = address;
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
        
        public Address getAddress() {
            return address;
        }
        
        public void setAddress(Address address) {
            this.address = address;
        }
        
        @Override
        public Object clone() throws CloneNotSupportedException {
            Customer cloned = (Customer) super.clone();
            if (this.address != null) {
                cloned.address = new Address(this.address.getLine(), this.address.getCity(), this.address.getPincode());
            }
            return cloned;
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
        
        @Override
        public String toString() {
            return "Account[" + accountNumber + ", " + ownerName + ", Balance: Rs. " + balance + "]";
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return accountNumber.equals(account.accountNumber);
        }
        
        @Override
        public int hashCode() {
            return accountNumber.hashCode();
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
        
        System.out.println(accounts[0].toString());
        System.out.println(accounts[1].toString());
        System.out.println(accounts[2].toString());
        
        Account acc1 = new Account("Test User", 100);
        Account acc2 = new Account("Test User", 200);
        acc2 = acc1;
        System.out.println("acc1.equals(acc2): " + acc1.equals(acc2));
        
        Object obj = accounts[0];
        if (obj instanceof Account) {
            System.out.println("Object is instance of Account");
        }
        
        try {
            Address addr = new Address("123 MG Road", "Bangalore", "560001");
            Customer customer = new Customer("Rahul Sharma", "rahul@mail.com", "9876543210", addr);
            Customer clonedCustomer = (Customer) customer.clone();
            System.out.println("Customer cloned: " + clonedCustomer.getName());
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported");
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
