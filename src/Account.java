import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void withDraw(BigDecimal amount){

    }

    public void deposit(BigDecimal amount){

    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
