package transactions;

import account.Account;
import customer.Customer;
import utils.InputReader;

import java.math.BigDecimal;

public class Transfer implements Transaction {
    public Transfer(Customer customer) {
        String targetAccountNumber = getTargetAccountNumber();

        Transaction.checkForDiscontinuedTransaction(targetAccountNumber.equalsIgnoreCase("x"), customer);

        Account targetAccount = Transaction.fetchAccountFromDatabase(targetAccountNumber);

        if (invalidTargetAccount(targetAccount)){
            new Transfer(customer);
        } else{
            BigDecimal amount = promptCustomerForAmount(customer);

            Withdraw.executeWithdrawal(customer, amount);

            targetAccount.deposit(amount);

            System.out.println("Transfer succeeded.");
        }
    }

    private boolean invalidTargetAccount(Account targetAccount) {
        if (targetAccount == null){
            System.out.println("The entered account number was invalid.");
            return true;
        } else {
            return false;
        }
    }

    private String getTargetAccountNumber() {
        String targetAccountNumber = InputReader.requestTextInput("Enter the account number of the account you want to " +
                "transfer money to. \n [Or enter 'x' to discontinue transaction]:");

        return targetAccountNumber;
    }
}
