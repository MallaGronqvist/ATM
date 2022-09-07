package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import utils.MenuPrinter;

import java.math.BigDecimal;

public class Deposit implements Transaction {
    public Deposit(Customer customer) {

        Account account = AccountDatabase.getAccount(customer.getAccountNumber());

        Transaction.confirmCustomersAccount(customer, account);

        BigDecimal amountToDeposit = promptCustomerForAmount(customer);

        account.deposit(amountToDeposit);

        displaySuccessfulDeposit(customer);
    }

    private void displaySuccessfulDeposit(Customer customer) {
        MenuPrinter.clearConsole();
        System.out.println("Deposit succeeded. See current balance below.");
        System.out.println();

        new ViewBalance(customer);
    }
}
