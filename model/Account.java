package model;

import model.annotation.Id;
import model.annotation.Positive;
import exception.InvalidAmountException;
import exception.InsufficientFundsException;
import exception.BankException;

public abstract class Account implements Transactable, InterestBearing {
    @Id
    private final String accountNumber;
    private String ownerName;
    @Positive
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
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        balance += amount;
    }
    
    @Override
    public boolean withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        if (!canWithdraw(amount)) {
            long shortfall = amount - balance;
            if (shortfall < 0) shortfall = amount;
            throw new InsufficientFundsException("Insufficient funds", shortfall);
        }
        balance -= amount;
        return true;
    }
    
    public void transfer(Account to, long amount) throws BankException {
        try {
            this.withdraw(amount);
            to.deposit(amount);
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer attempt completed");
        }
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
