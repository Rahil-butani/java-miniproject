package model;

import java.io.Serializable;

public class FixedDepositAccount extends Account implements Serializable {
    private static final long serialVersionUID = 1L;
    public FixedDepositAccount(String ownerName, long openingBalance) {
        super(ownerName, openingBalance);
    }
    
    public FixedDepositAccount(String ownerName) {
        super(ownerName);
    }
    
    @Override
    public double interestRate() {
        return 7.0;
    }
    
    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}
