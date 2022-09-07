package menus.customerMenu;

import customer.Customer;
import menus.ChangeCredentialsMenu.ChangeCredentialsMenu;
import menus.mainMenu.MainMenu;
import menus.menuCommons.MenuModel;
import transactions.Deposit;
import transactions.Transfer;
import transactions.ViewBalance;
import transactions.Withdraw;
import utils.MenuPrinter;

import java.util.List;

public class CustomerMenuModel implements MenuModel {
    private final List<String> menuOptions = List.of("View balance", "Withdraw money", "Deposit money",
            "Transfer money", "Change my customer credentials", "Sign out");

    Customer customer;

    public CustomerMenuModel(Customer customer) {
        this.customer = customer;
    }

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {

        MenuPrinter.clearConsole();

        switch (selectedOption) {
            case 1 -> new ViewBalance(customer);
            case 2 -> new Withdraw(customer);
            case 3 -> new Deposit(customer);
            case 4 -> new Transfer(customer);
            case 5 -> new ChangeCredentialsMenu(customer);
            case 6 -> new MainMenu();
            default -> throw new IndexOutOfBoundsException();
        }

        MenuPrinter.waitForEnter();

        new CustomerMenu(customer);
    }
}
