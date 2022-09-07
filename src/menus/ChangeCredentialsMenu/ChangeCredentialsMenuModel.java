package menus.ChangeCredentialsMenu;

import customer.Customer;
import customer.CustomerDatabase;
import customer.customerAuthentication.SignUp;
import menus.menuCommons.MenuModel;

import java.util.List;

public class ChangeCredentialsMenuModel implements MenuModel {

    List<String> options = List.of("Change username", "Change password");
    Customer customer;

    public ChangeCredentialsMenuModel(Customer customer) {
        this.customer = customer;
    }

    private static void changeUsername(Customer customer) {

        CustomerDatabase.updateUsername(customer);

        System.out.println("Your username was changed successfully.");

    }

    private static void changePassword(Customer customer) {

        SignUp.attemptSetPassword(customer);

        System.out.println("Your password was changed successfully.");

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
}
