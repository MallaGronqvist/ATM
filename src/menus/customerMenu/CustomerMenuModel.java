package menus.customerMenu;

import customer.Customer;
import customerOperations.*;
import customerOperations.customerAuthentication.ChangeCredentials;
import menus.mainMenu.MainMenu;
import menus.menuCommons.MenuModel;
import utils.MenuPrinter;

import java.util.List;


public class CustomerMenuModel extends MenuModel {
    private final Customer customer;

    public CustomerMenuModel(Customer customer) {
        // Formatting -1, even if it takes more space, if your list does not fit in 1 line, then put 1 item per line.
        super.options = List.of(
                "View balance",
                "Withdraw money",
                "Deposit money",
                "Transfer money",
                "Change my customer credentials",
                "Sign out"
        );
        this.customer = customer;
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {

        MenuPrinter.clearConsole();

        switch (selectedOption) {
            case 1 -> new ViewBalance(customer);
            case 2 -> new Withdraw(customer);
            case 3 -> new Deposit(customer);
            case 4 -> new Transfer(customer);
            case 5 -> new ChangeCredentials(customer);
            case 6 -> new MainMenu();
            default -> throw new IndexOutOfBoundsException();
        }

        /**
         * Side effects -1
         * What's the purpose of this method?
         * This is why the app sometimes feels glitchy as sometimes it needs more than 1 enter to work properly.
         * Any input job should be handled by the controller not the model.
         *
         * The problem with this is that if a new developer tries to modify your code, it will expend hours trying to
         * debug your controller for the pesky enter bug, when the code that generates it is in the model.
         */
        MenuPrinter.waitForEnter();

        new CustomerMenu(customer);
    }
}
