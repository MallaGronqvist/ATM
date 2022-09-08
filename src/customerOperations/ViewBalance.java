package customerOperations;

import account.Account;
import account.AccountDatabase;
import customer.Customer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class ViewBalance implements Transaction {

    public ViewBalance(Customer customer) {
        System.out.println("***View Balance***");
        System.out.println();
        System.out.println("Account owner: " + customer.getFullName());

        Account account = AccountDatabase.getAccount(customer.getAccountNumber());

        Transaction.confirmCustomersAccount(customer, account);

        System.out.println("Account number: " + account.getAccountNumber());

        System.out.print("Current balance: ");

        displayCurrency(account.getBalance());
    }

    public static void displayCurrency(BigDecimal money) {
        BigDecimal displayValue = money.setScale(2, RoundingMode.HALF_EVEN);

        NumberFormat swedishFormat = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        swedishFormat.setMinimumFractionDigits(2);
        swedishFormat.setMaximumFractionDigits(2);

        System.out.println(swedishFormat.format(displayValue.doubleValue()));
    }
}
