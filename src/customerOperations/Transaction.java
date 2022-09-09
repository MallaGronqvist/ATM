package customerOperations;

import account.Account;
import customer.Customer;
import menus.customerMenu.CustomerMenu;
import utils.InputReader;
import utils.MenuPrinter;

import java.math.BigDecimal;

public interface Transaction {
    int maxLimit = 20000;
    int minLimit = 1;

    static void confirmCustomersAccount(Customer customer, Account customersAccount) {
        if (customersAccount == null) {
            System.out.println("Your account is unavailable at the moment. Contact your bank office.");
            new CustomerMenu(customer);
        }
    }

    default void checkForDiscontinuedTransaction(boolean discontinue, Customer customer) {
        if (discontinue) {
            new CustomerMenu(customer);
        }
    }

    default BigDecimal promptCustomerForAmount(Customer customer) {
        BigDecimal amount;

        try {
            amount = requestAmount();

            checkForDiscontinuedTransaction((amount == null), customer);

            // Amount shouldn't be negative or beyond the allowed amount range.
            if ((amount.compareTo(new BigDecimal(0)) == -1) || (amount.compareTo(new BigDecimal(maxLimit)) == 1)
                    || amount.compareTo(new BigDecimal(minLimit)) == -1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            MenuPrinter.clearConsole();
            System.out.println("Invalid amount.");
            System.out.println();

            amount = promptCustomerForAmount(customer);
        }

        return amount;
    }

    private BigDecimal requestAmount() throws NumberFormatException {
        String input = InputReader.requestInput("Enter amount in digits. \nAllowed amount: " +
                minLimit + " - " + maxLimit + " kr.", "amount");

        input = input.replaceAll(",", ".");

        // Only two decimals are allowed.
        if (getNumberOfDecimalPlaces(input) > 2) {
            throw new NumberFormatException();
        }

        if (input.equalsIgnoreCase("x")) {
            return null;
        } else {
            return new BigDecimal(input);
        }
    }

    private int getNumberOfDecimalPlaces(String input) {
        int index = input.indexOf(".");
        return index < 0 ? 0 : input.length() - index - 1;
    }
}
