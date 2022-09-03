package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.InputReader;

import java.math.BigDecimal;

public class Deposit {
    public Deposit(Customer customer) {
        // Ask about this exception handling. Is necessary or not?
        // If yes, implement in other places that use getAccount()
        Account account = null;
        try {
            account = AccountDatabase.getAccount(customer.getAccountNumber());
        } catch (NullPointerException e) {
            System.out.println("Your account is unavailable at the moment. Contact your bank.");
            new CustomerMenu(customer);
        }

        BigDecimal amountToDeposit = new BigDecimal(0);
        try {
            amountToDeposit = requestAmountToDeposit();

            if(amountToDeposit.compareTo(new BigDecimal(0))== -1){
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException e) {
            System.out.println("You entered invalid characters or a negative number.");
            new Deposit(customer);
        }

        account.deposit(amountToDeposit);
        System.out.println("Deposit succeeded.");
    }

    private BigDecimal requestAmountToDeposit() throws NumberFormatException{
        String input = InputReader.requestTextInput("Enter amount to deposit:");
        BigDecimal amount = new BigDecimal(input);
        return amount;
    }
}
