package menus.customerMenu;

import menus.mainMenu.MainMenu;
import transactions.Deposit;
import transactions.Withdraw;
import transactions.ViewBalance;
import customer.Customer;
import utils.MenuPrinter;

import java.util.List;

public class CustomerMenuModel {
    private final List<String> menuOptions = List.of("View balance", "Withdraw money", "Deposit money",
            "Transfer money", "Edit my customer details", "Sign out");

    Customer customer;

    public CustomerMenuModel(Customer customer) {
        this.customer = customer;
    }

    public List<String> getMenuOptions () {
        return menuOptions;
    }

    public void processOption ( int selectedOption) throws IndexOutOfBoundsException {

        MenuPrinter.clearConsole();

        switch (selectedOption) {
            case 1 -> new ViewBalance(customer);
            case 2 -> new Withdraw(customer);
            case 3 -> new Deposit(customer);
            case 4 -> System.out.println("transfer");
            case 5 -> System.out.println("edit details");
            case 6 -> new MainMenu();
            default -> throw new IndexOutOfBoundsException();
        }

        MenuPrinter.waitForEnter();

        new CustomerMenu(customer);
    }
}
