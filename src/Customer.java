import java.security.NoSuchAlgorithmException;

public class Customer {
    private final String fullName;
    private String userName;
    private String password;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private String accountNumber;
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
        String SHA_256_secured_password = HashGenerator.get_SHA_256_SecurePassword(password, hashingSalt);
        this.password = SHA_256_secured_password;
    }
}
