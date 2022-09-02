package menus.customerMenu;

import utils.MenuPrinter;

import java.util.List;

public class CustomerMenuView {

    public CustomerMenuView(List<String> menuOptions) {
        MenuPrinter.clearConsole();

        System.out.println("Choose an operation:");

        MenuPrinter.listOptions(menuOptions);

        requestUserInput();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }
}
