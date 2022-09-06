package menus.EditCustomerMenu;

import customer.Customer;
import customer.CustomerDatabase;
import menus.menuCommons.MenuModel;
import signInAndSignUp.SignUp;

import java.util.List;

public class EditCustomerMenuModel implements MenuModel {

    List<String>options = List.of("Change username", "Change password");
    Customer customer;

    public EditCustomerMenuModel(Customer customer) {
        this.customer = customer;
    }

    public List<String> getMenuOptions() {
        return options;
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> changeUsername(customer);
            case 2 -> changePassword(customer);
            default -> throw new IndexOutOfBoundsException();
        }
    }

    public static void changeUsername(Customer customer){

        CustomerDatabase.updateUsername(customer);
    }

    public static void changePassword(Customer customer){

        SignUp.attemptSetPassword(customer);
    }
}
