package menus.mainMenu;

import java.util.Scanner;

public class MainMenuController {
    private final MainMenuModel model;
    private final MainMenuView view;

    public MainMenuController(MainMenuModel model, MainMenuView view) {
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
