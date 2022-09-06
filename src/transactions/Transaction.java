package transactions;

import account.Account;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.InputReader;
import utils.MenuPrinter;

import java.math.BigDecimal;

public interface Transaction {
    default BigDecimal promptCustomerForAmount(Customer customer){
        MenuPrinter.clearConsole();

        BigDecimal amount = null;

        try {
            amount = requestAmount();

            checkForDiscontinuedTransaction((amount == null), customer);


            final int maxLimit = 20000;
            // Amount shouldn't be negative or exceed the max limit of 20 000.
            if((amount.compareTo(new BigDecimal(0))== -1 )|| (amount.compareTo(new BigDecimal(maxLimit)) == 1)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("You entered invalid characters " +
                    "\n or an amount that exceeds the max limit of 20 000.");

            amount = promptCustomerForAmount(customer);
        }

        return amount;
    }

    private BigDecimal requestAmount() throws NumberFormatException{
        String input = InputReader.requestTextInput("Enter amount [Or enter 'x' to discontinue transaction]:");

        input = input.replaceAll(",", ".");

        if(getNumberOfDecimalPlaces(input) > 2){
            throw new NumberFormatException();
        }

        if (input.equalsIgnoreCase("x")){
            return null;
        } else {
            BigDecimal amount = new BigDecimal(input);

            return amount;
        }

    }

    private int getNumberOfDecimalPlaces(String input) {
        int index = input.indexOf(".");
        return index < 0 ? 0 : input.length() - index - 1;
    }

    static void confirmSourceAccount(Customer customer, Account customersAccount) {
        if (customersAccount == null){
            System.out.println("Your account is unavailable at the moment. Contact your bank office.");
            new CustomerMenu(customer);
        }
    }

    static void checkForDiscontinuedTransaction(boolean discontinue, Customer customer) {
        if(discontinue){
            new CustomerMenu(customer);
        }
    }
}
