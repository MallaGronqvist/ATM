package menus.changeCredentialsMenu;

import customer.Customer;
import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;

public class ChangeCredentialsMenu {
    public ChangeCredentialsMenu(Customer customer) {
        ChangeCredentialsMenuModel model = new ChangeCredentialsMenuModel(customer);
        MenuView view = new MenuView("***Change customer credentials***", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
