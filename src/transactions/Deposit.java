package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;

import java.math.BigDecimal;

public class Deposit implements Transaction {
    public Deposit(Customer customer) {

        Account account = AccountDatabase.getAccount(customer.getAccountNumber());
        // Rename "source account"
        Transaction.confirmSourceAccount(customer, account);

        BigDecimal amountToDeposit = promptCustomerForAmount(customer);

        account.deposit(amountToDeposit);

        System.out.println("Deposit succeeded. See current balance below.");
        System.out.println();

        new ViewBalance(customer);
    }
}
