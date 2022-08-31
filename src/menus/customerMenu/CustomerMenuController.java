package menus.customerMenu;

import menus.mainMenu.MainMenuModel;
import menus.mainMenu.MainMenuView;

import java.util.Scanner;

public class CustomerMenuController {
    private final CustomerMenuModel model;
    private final CustomerMenuView view;

    public CustomerMenuController(CustomerMenuModel model, CustomerMenuView view) {
        this.model = model;
        this.view = view;
    }

    public void readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);

            model.processOption(selectedOption);

        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            view.printInvalidOption();
            view.requestUserInput();
            readUserInput();
        }
    }
}
