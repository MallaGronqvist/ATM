package menus.EditCustomerMenu;

import customer.Customer;
import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;

public class EditCustomerMenu {
    public EditCustomerMenu(Customer customer) {
        EditCustomerMenuModel model = new EditCustomerMenuModel(customer);
        MenuView view = new MenuView("***Edit customer details***", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
