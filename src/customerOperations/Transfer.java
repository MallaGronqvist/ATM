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

        String recipientAccountNumber = InputReader.requestInput("Enter the recipient's account number.",
                "accountNumber");

        checkForDiscontinuedTransaction(recipientAccountNumber.equalsIgnoreCase("x"), customer);

        Account recipientAccount = AccountDatabase.getAccount(recipientAccountNumber);

        if (invalidTargetAccount(recipientAccount) ||
                sendingAndRecipientAccountSame(customer.getAccountNumber(), recipientAccountNumber)) {

            new Transfer(customer);

        } else {
            BigDecimal amount = promptCustomerForAmount(customer);

            Withdraw.executeWithdrawal(customer, amount);

            recipientAccount.deposit(amount);

            displaySuccessfulTransfer(customer, recipientAccount, amount);
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
            MenuPrinter.clearConsole();
            System.out.println("The entered account number was invalid. Try again.");
            System.out.println();
            return true;
        } else {
            return false;
        }
    }

    private boolean sendingAndRecipientAccountSame(String sendingAccountNr, String recipientAccountNr){
        boolean result = false;

        // safeguard
        if(sendingAccountNr.equals(recipientAccountNr)){
            System.out.println("The entered recipient account is the same as the sending account.");
            System.out.println();
            result = true;
        }

        return result;
    }
}
