package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import signInAndSignUp.InputReader;

import java.math.BigDecimal;

public class Withdraw {
    public Withdraw(Customer customer) {
        try {
            BigDecimal amountToWithDraw = requestAmountToWithDraw();
            Account account = AccountDatabase.getAccount(customer.getAccountNumber());
            account.withDraw(amountToWithDraw);
            System.out.println("Withdrawal succeeded.");
        } catch (IllegalArgumentException e) {
            System.out.println("You entered invalid characters or a negative number.");
            new Withdraw(customer);
        } catch (Account.InsufficientBalanceException e) {
            System.out.println("Insufficient balance.");
        }
    }

    private BigDecimal requestAmountToWithDraw() throws NumberFormatException{
        String input = InputReader.requestTextInput("Enter amount to withdraw:");
        BigDecimal amount = new BigDecimal(input);
        return amount;
    }
}
