package model;

public abstract class Account implements Transactable, InterestBearing {
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
    
    @Override
    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    @Override
    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public abstract boolean canWithdraw(long amount);
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    @Override
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
