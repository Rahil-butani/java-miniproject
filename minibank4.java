import java.util.Scanner;
import java.util.regex.Pattern;

public class minibank4 {
    
    static class Validator {
        private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
        private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
        private static final Pattern IFSC_PATTERN = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");
        
        public static boolean isValidMobile(String mobile) {
            if (mobile == null) return false;
            return MOBILE_PATTERN.matcher(mobile).matches();
        }
        
        public static boolean isValidEmail(String email) {
            if (email == null) return false;
            return EMAIL_PATTERN.matcher(email).matches();
        }
        
        public static boolean isValidPan(String pan) {
            if (pan == null) return false;
            return PAN_PATTERN.matcher(pan).matches();
        }
        
        public static boolean isValidIfsc(String ifsc) {
            if (ifsc == null) return false;
            return IFSC_PATTERN.matcher(ifsc).matches();
        }
    }
    
    enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
    
    record Command(TransactionType type, String accountNumber, long amount) {}
    
    static class CommandParser {
        public static Command parse(String line) {
            if (line == null) return null;
            String[] parts = line.trim().split("\\s+");
            if (parts.length < 3) return null;
            TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
            String accountNumber = parts[1];
            long amount = Long.parseLong(parts[2]);
            return new Command(type, accountNumber, amount);
        }
    }
    
    static class StatementFormatter {
        public static String buildStatement(Account account) {
            StringBuilder sb = new StringBuilder();
            sb.append("=== Account Statement ===\n");
            sb.append("Account Number: ").append(account.getAccountNumber()).append("\n");
            sb.append("Owner Name: ").append(account.getOwnerName()).append("\n");
            sb.append("Balance: Rs. ").append(account.getBalance()).append("\n");
            sb.append("=========================");
            return sb.toString();
        }
    }
    
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
        
        System.out.println("Mobile 9876543210 valid: " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile 12345 valid: " + Validator.isValidMobile("12345"));
        System.out.println("Email test@mail.com valid: " + Validator.isValidEmail("test@mail.com"));
        System.out.println("Email invalid.com valid: " + Validator.isValidEmail("invalid.com"));
        System.out.println("PAN ABCDE1234F valid: " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("PAN ABC123 valid: " + Validator.isValidPan("ABC123"));
        System.out.println("IFSC SBIN0001234 valid: " + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("IFSC ABCD12345 valid: " + Validator.isValidIfsc("ABCD12345"));
        
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        if (cmd != null) {
            System.out.println("\nParsed Command: " + cmd.type() + ", " + cmd.accountNumber() + ", " + cmd.amount());
        }
        
        Account acc = new Account("Rahul Sharma", 1000);
        System.out.println("\n" + StatementFormatter.buildStatement(acc));
        
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
