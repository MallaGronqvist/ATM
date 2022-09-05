package transactions;

import account.Account;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.MenuPrinter;

import java.math.BigDecimal;

public class Withdraw implements Transaction{
    public Withdraw(Customer customer) {
        BigDecimal amountToWithDraw = promptCustomerForAmount(customer);
        executeWithdrawal(customer, amountToWithDraw);
        System.out.println("Withdrawal succeeded.");
        new ViewBalance(customer);
    }

    static void executeWithdrawal(Customer customer, BigDecimal amountToWithDraw) {
        Account account = Transaction.fetchAccountFromDatabase(customer.getAccountNumber());

        Transaction.confirmSourceAccount(customer, account);

        try {
            account.withDraw(amountToWithDraw);

        } catch (Account.InsufficientBalanceException e) {
            System.out.println("Insufficient balance.");
            MenuPrinter.waitForEnter();
            new CustomerMenu(customer);
        }
    }
}
