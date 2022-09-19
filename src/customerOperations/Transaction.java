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

    /**
     * Comments -1
     * Literally everything you said in the comment is what I can read and understand from the code.
     */
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

            // Nesting -1
            // This is a "special" verification. Should be handled as a separate method.
            // See the "generateBorderCell" example in the Code Quality Pyramid example.

            // Amount shouldn't be negative or beyond the allowed amount range.
            // Also this shows A LOT of warnings in Intelli J. Moving it to another place will make it easier to debug
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

    // Comments -1
    // Naming -1
    // Use a variable instead of writing a comment
    // Nesting -1 The return will stop your code, thus you don't need to use if/else,
    private BigDecimal requestAmount() throws NumberFormatException {
        int decimalNumbersAllowed = 2;
        String prompt = String.format("🐰 Enter amount in digits. \nAllowed amount: %d - %d kr", minLimit, maxLimit);
        String input = InputReader.requestInput(prompt, "amount");

        input = input.replaceAll(",", ".");

        // Safeguards
        if (getNumberOfDecimalPlaces(input) > decimalNumbersAllowed) {
            throw new NumberFormatException();
        }

        if (input.equalsIgnoreCase("x")) {
            return null;
        }

        return new BigDecimal(input);
    }

    private int getNumberOfDecimalPlaces(String input) {
        int index = input.indexOf(".");

        return index < 0 ? 0 : input.length() - index - 1;
    }
}
