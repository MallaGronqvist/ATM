package transactions;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import utils.InputReader;
import utils.MenuPrinter;

import java.math.BigDecimal;

import static transactions.Transaction.checkForDiscontinuedTransaction;
import static transactions.ViewBalance.displayCurrency;

public class Transfer implements Transaction {
    public Transfer(Customer customer) {
        String targetAccountNumber = InputReader.requestTextInput("Enter the recipient's account number." +
                "\n[ Or enter 'x' to discontinue transaction ]:");

        checkForDiscontinuedTransaction(targetAccountNumber.equalsIgnoreCase("x"), customer);

        Account targetAccount = AccountDatabase.getAccount(targetAccountNumber);

        if (invalidTargetAccount(targetAccount)) {
            new Transfer(customer);

        } else {
            BigDecimal amount = promptCustomerForAmount(customer);

            Withdraw.executeWithdrawal(customer, amount);

            targetAccount.deposit(amount);

            displaySuccessfulTransfer(customer, targetAccount, amount);
        }
    }

    private void displaySuccessfulTransfer(Customer customer, Account targetAccount, BigDecimal amount) {
        MenuPrinter.clearConsole();

        System.out.println("Successfully transferred: ");

        displayCurrency(amount);

        System.out.println("Recipient account: ");
        System.out.println(targetAccount.getAccountNumber());
        System.out.println();
        System.out.println("See current balance below.");
        System.out.println();

        new ViewBalance(customer);
    }

    private boolean invalidTargetAccount(Account targetAccount) {
        if (targetAccount == null) {
            System.out.println("The entered account number was invalid.");
            return true;
        } else {
            return false;
        }
    }
}
