package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.InputReader;

import java.math.BigDecimal;

public class Withdraw implements Transaction{
    public Withdraw(Customer customer) {
        Account account = fetchAccountFromDatabase(customer.getAccountNumber());

        if (account == null){
            System.out.println("Your account is unavailable at the moment. Contact your bank office.");
            new CustomerMenu(customer);
        }

        BigDecimal amountToWithDraw = getAmountFromCustomer();

        if(amountToWithDraw == null){
            new CustomerMenu(customer);
        }

        try {
            account.withDraw(amountToWithDraw);
            System.out.println("Withdrawal succeeded.");
        } catch (Account.InsufficientBalanceException e) {
            System.out.println("Insufficient balance.");
        }
    }
}
