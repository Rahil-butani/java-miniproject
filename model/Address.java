package model;

public class Address {
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
