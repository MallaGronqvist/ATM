package menus.mainMenu;

import customer.customerAuthentication.SignIn;
import customer.customerAuthentication.SignUp;
import menus.menuCommons.MenuModel;

import java.util.List;


public class MainMenuModel extends MenuModel {

    public MainMenuModel() {
        super.options = List.of("Sign in", "Sign up");
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {

        switch (selectedOption) {
            case 1 -> new SignIn();
            case 2 -> new SignUp();
            default -> throw new IndexOutOfBoundsException();
        }

    }
}
