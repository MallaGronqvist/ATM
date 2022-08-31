package operations;

import signInAndSignUp.Account;
import signInAndSignUp.AccountDatabase;
import signInAndSignUp.Customer;
import utils.MenuPrinter;

public class ViewBalance {

    public ViewBalance(Customer customer) {
        MenuPrinter.clearConsole();

        Account account = AccountDatabase.getAccount(customer.getAccountNumber());
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Current balance: " + account.getBalance());

        MenuPrinter.waitForEnter();
    }
}
