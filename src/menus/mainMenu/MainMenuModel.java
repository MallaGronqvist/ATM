package menus.mainMenu;

import menus.menuCommons.MenuModel;
import signInAndSignUp.SignIn;
import signInAndSignUp.SignUp;

import java.util.List;


public class MainMenuModel implements MenuModel {
    private final List<String> menuOptions = List.of("Sign in", "Sign up");

    public List<String> getMenuOptions () {
        return menuOptions;
    }

    public void processOption ( int selectedOption) throws IndexOutOfBoundsException {

        switch (selectedOption) {
            case 1 -> new SignIn();
            case 2 -> new SignUp();
            default -> throw new IndexOutOfBoundsException();
        }

    }
}
