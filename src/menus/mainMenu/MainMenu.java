package menus.mainMenu;

import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;

// Good
public class MainMenu {

    public MainMenu() {
        MainMenuModel model = new MainMenuModel();
        MenuView view = new MenuView("***ATM***", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
