package signInAndSignUp;

import java.security.NoSuchAlgorithmException;

public class Customer {
    private final String fullName;
    private String userName;

    public String getPassword() {
        return password;
    }

    private String password;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private String accountNumber;

    public String getHashingSalt() {
        return hashingSalt;
    }

    private String hashingSalt;
    private int customerID;

    public Customer(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.hashingSalt = HashGenerator.getSalt();
        this.password = HashGenerator.get_SHA_256_SecurePassword(password, hashingSalt);
    }
}
