package customerOperations;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import utils.InputReader;
import utils.MenuPrinter;

import java.math.BigDecimal;

import static customerOperations.ViewBalance.displayCurrency;

public class Transfer implements Transaction {
    public Transfer(Customer customer) {
        System.out.println("***Transfer***");
        System.out.println();

        String targetAccountNumber = getTargetAccountNumber();

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

    private String getTargetAccountNumber() {
        return InputReader.requestInput("Enter the recipient's account number.", "accountNumber");
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
            MenuPrinter.clearConsole();
            System.out.println("The entered account number was invalid. Try again.");
            System.out.println();
            return true;
        } else {
            return false;
        }
    }
}
