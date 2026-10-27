package model;

import java.io.Serializable;

public class Customer implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
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
