package customer;

import customer.customerAuthentication.HashGenerator;

import java.security.NoSuchAlgorithmException;

public class Customer {
    private final String FULL_NAME;
    private String userName;
    private String password;
    private String accountNumber;
    private String hashingSalt;
    private int customerID;

    public Customer(String fullName, String userName) {
        this.FULL_NAME = fullName;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.hashingSalt = HashGenerator.getSalt();
        this.password = HashGenerator.get_SHA_256_SecurePassword(password, hashingSalt);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHashingSalt() {
        return hashingSalt;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getFullName() {
        return FULL_NAME;
    }
}
