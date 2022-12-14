package customer;

import account.Account;
import account.AccountDatabase;
import customerOperations.customerAuthentication.HashGenerator;

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
        this.password = HashGenerator.get_SHA_256_securePassword(password, hashingSalt);
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

    public void assignAccount() {
        Account newAccount = new Account();
        newAccount.setAccountNumber(AccountDatabase.generateNewAccountNumber());
        this.setAccountNumber(newAccount.getAccountNumber());
        AccountDatabase.addAccount(newAccount);
    }
}
