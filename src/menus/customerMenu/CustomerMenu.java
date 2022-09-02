package menus.customerMenu;

import customer.Customer;

public class CustomerMenu {
    public CustomerMenu(Customer customer) {
        CustomerMenuModel model = new CustomerMenuModel(customer);
        CustomerMenuView view = new CustomerMenuView(model.getMenuOptions());
        CustomerMenuController controller = new CustomerMenuController(model, view);

        controller.readUserInput();
    }
}
