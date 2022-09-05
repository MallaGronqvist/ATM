package transactions;

import account.Account;
import account.AccountDatabase;
import utils.InputReader;

import java.math.BigDecimal;

public interface Transaction {
    default BigDecimal getAmountFromCustomer(){
        BigDecimal amount = null;

        try {
            amount = requestAmount();
            if(amount == null){
                return amount;
            }

            // Amount shouldn't be negative or exceed the max limit of 20 000.
            final int maxLimit = 20000;
            if((amount.compareTo(new BigDecimal(0))== -1 )|| (amount.compareTo(new BigDecimal(maxLimit)) == 1)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: You entered invalid characters, a negative number " +
                    "or an amount that exceeds the max limit of 20 000.");
            amount = getAmountFromCustomer();
        }

        return amount;
    }

    private BigDecimal requestAmount() throws NumberFormatException{
        String input = InputReader.requestTextInput("Enter amount [Or enter 'x' to discontinue transaction]:");

        if (input.equalsIgnoreCase("x")){
            return null;
        } else {
            BigDecimal amount = new BigDecimal(input);

            return amount;
        }

    }

    default Account fetchAccountFromDatabase(String accountNumber){
        Account account = null;

        try {
            account = AccountDatabase.getAccount(accountNumber);
        } catch (NullPointerException e) {
            System.out.println("No account could be found with the account number.");
        }

        return account;
    }
}
