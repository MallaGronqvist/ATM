package menus.customerMenu;

import customer.Customer;
import menus.menuCommons.MenuController;
import menus.menuCommons.MenuView;

// good as well
public class CustomerMenu {
    public CustomerMenu(Customer customer) {
        CustomerMenuModel model = new CustomerMenuModel(customer);
        MenuView view = new MenuView("***Customer operations***", model.getMenuOptions());
        MenuController controller = new MenuController(model, view);

        controller.readUserInput();
    }
}
