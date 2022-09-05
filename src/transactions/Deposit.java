package transactions;

import account.Account;
import customer.Customer;

import java.math.BigDecimal;

public class Deposit implements Transaction {
    public Deposit(Customer customer) {

        Account account = Transaction.fetchAccountFromDatabase(customer.getAccountNumber());

        Transaction.confirmSourceAccount(customer, account);

        BigDecimal amountToDeposit = getAmountFromCustomer(customer);

        account.deposit(amountToDeposit);

        System.out.println("Deposit succeeded.");
    }
}
