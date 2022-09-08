package menus.customerMenu;

import customer.Customer;
import customerOperations.*;
import menus.changeCredentialsMenu.ChangeCredentialsMenu;
import menus.mainMenu.MainMenu;
import menus.menuCommons.MenuModel;
import utils.MenuPrinter;

import java.util.List;

public class CustomerMenuModel extends MenuModel {
    private final Customer customer;

    public CustomerMenuModel(Customer customer) {
        super.options = List.of("View balance", "Withdraw money", "Deposit money",
                "Transfer money", "Change my customer credentials", "Sign out");
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

        MenuPrinter.waitForEnter();

        new CustomerMenu(customer);
    }
}
