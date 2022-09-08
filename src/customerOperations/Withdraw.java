package customerOperations;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.MenuPrinter;

import java.awt.*;
import java.math.BigDecimal;

public class Withdraw implements Transaction {
    public Withdraw(Customer customer) {
        System.out.println("***Withdraw***");
        System.out.println();

        BigDecimal amountToWithDraw = promptCustomerForAmount(customer);

        executeWithdrawal(customer, amountToWithDraw);

        MenuPrinter.clearConsole();
        System.out.println("Withdrawal succeeded. See current balance below.");
        System.out.println();

        new ViewBalance(customer);
    }

    static void executeWithdrawal(Customer customer, BigDecimal amountToWithDraw) {
        Account account = AccountDatabase.getAccount(customer.getAccountNumber());

        Transaction.confirmCustomersAccount(customer, account);

        try {
            account.withDraw(amountToWithDraw);

        } catch (Account.InsufficientBalanceException e) {
            MenuPrinter.clearConsole();
            System.out.println("Insufficient balance. See current balance below.");
            System.out.println();

            new ViewBalance(customer);

            MenuPrinter.waitForEnter();

            new CustomerMenu(customer);
        }
    }
}
