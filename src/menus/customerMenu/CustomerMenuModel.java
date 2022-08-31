package menus.customerMenu;

import menus.mainMenu.MainMenu;
import operations.ViewBalance;
import signInAndSignUp.Customer;

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

        switch (selectedOption) {
            case 1 -> new ViewBalance(customer);
            case 2 -> System.out.println("withdraw");
            case 3 -> System.out.println("deposit");
            case 4 -> System.out.println("transfer");
            case 5 -> System.out.println("edit details");
            case 6 -> new MainMenu();
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
