package signInAndSignUp;

import customer.Customer;
import customer.CustomerDatabase;
import customer.HashGenerator;
import menus.customerMenu.CustomerMenu;
import menus.mainMenu.MainMenu;
import utils.InputReader;
import utils.MenuPrinter;

import java.security.NoSuchAlgorithmException;

public class SignIn {
    public SignIn() {
        try {
            Customer customer = getCustomerByUserName();

            checkPassword(customer);

            new CustomerMenu(customer);

        } catch (NullPointerException e) {
            System.out.println("No account was found with that username.");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An unexpected error has occurred. You cannot sign in at the moment.");
        } catch (IncorrectPasswordException e) {
            System.out.println("You entered an incorrect password.");
        }
        MenuPrinter.waitForEnter();
        new MainMenu();
    }

    private static Customer getCustomerByUserName()throws NullPointerException{
        String userName = InputReader.requestTextInput("Enter username:");

        return CustomerDatabase.getCustomer(userName);
    }

    private static void checkPassword(Customer customer) throws NoSuchAlgorithmException, IncorrectPasswordException {
        String password = InputReader.requestTextInput("Enter password:");
        String SHA_256_convertedPassword =
                HashGenerator.get_SHA_256_SecurePassword(password, customer.getHashingSalt());

        if(!customer.getPassword().equals(SHA_256_convertedPassword)){
            throw new IncorrectPasswordException(password);
        }
    }

    static class IncorrectPasswordException extends Exception {
        String incorrectPassword;

        IncorrectPasswordException(String password) {
          incorrectPassword = password;
        }
    }
}
