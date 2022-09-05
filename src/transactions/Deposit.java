package transactions;

import account.Account;
import customer.Customer;
import menus.customerMenu.CustomerMenu;

import java.math.BigDecimal;

public class Deposit implements Transaction {
    public Deposit(Customer customer) {

        Account account = fetchAccountFromDatabase(customer.getAccountNumber());

        if (account == null){
            System.out.println("Your account is unavailable at the moment. Contact your bank office.");
            new CustomerMenu(customer);
        }

        BigDecimal amountToDeposit = getAmountFromCustomer();

        if(amountToDeposit == null){
            new CustomerMenu(customer);
        }

        account.deposit(amountToDeposit);

        System.out.println("Deposit succeeded.");
    }
}
