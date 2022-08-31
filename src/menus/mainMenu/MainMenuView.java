package menus.mainMenu;

import utils.MenuPrinter;

import java.util.List;

public class MainMenuView {

    public MainMenuView(List<String> menuOptions) {
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
