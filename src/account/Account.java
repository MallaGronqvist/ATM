package account;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private BigDecimal balance;

    public Account() {
        accountNumber = "";
        balance = new BigDecimal(Integer.toString(0));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withDraw(BigDecimal amount) throws InsufficientBalanceException {
        // If balance is less than the amount to withdraw.
        if (this.balance.compareTo(amount) == -1) {
            throw new InsufficientBalanceException();
        }

        this.balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static class InsufficientBalanceException extends Exception {
    }
}
