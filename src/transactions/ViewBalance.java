package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import utils.MenuPrinter;

public class ViewBalance implements Transaction{

    public ViewBalance(Customer customer) {
        System.out.println("Testing purposes; customer id: " + customer.getCustomerID());
        Account account = fetchAccountFromDatabase(customer.getAccountNumber());
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Current balance: " + account.getBalance());
    }
}
