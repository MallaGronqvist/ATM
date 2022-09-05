package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import utils.MenuPrinter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class ViewBalance implements Transaction{

    public ViewBalance(Customer customer) {
        System.out.println("Testing purposes; customer id: " + customer.getCustomerID());
        Account account = Transaction.fetchAccountFromDatabase(customer.getAccountNumber());
        Transaction.confirmSourceAccount(customer, account);
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Current balance: " + account.getBalance());
        displayMoney(account.getBalance());

    }

    public void displayMoney(BigDecimal money){
        BigDecimal displayVal = money.setScale(2, RoundingMode.HALF_EVEN);

        NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(new Locale("sv", "SE"));
        usdCostFormat.setMinimumFractionDigits( 2 );
        usdCostFormat.setMaximumFractionDigits( 2 );
        System.out.println(usdCostFormat.format(displayVal.doubleValue()) );
    }
}
